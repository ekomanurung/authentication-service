package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.configuration.properties.AuthenticationProperties;
import com.gusuran.authentication.model.User;
import com.gusuran.authentication.model.exception.BusinessExceptionBuilder;
import com.gusuran.authentication.model.exception.ErrorMapping;
import com.gusuran.authentication.repository.api.UserRepository;
import com.gusuran.authentication.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProperties authenticationProperties;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationProperties authenticationProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public Single<User> normalAuthentication(String username, String rawPassword) {
        return Single.create(subscriber -> {
            User currentUser = userRepository
                    .findByUsername(username)
                    .map(this::validateUserAccount)
                    .map(user -> validateLogin(rawPassword, user))
                    .orElseThrow(() -> new BusinessExceptionBuilder()
                            .withErrorCode(ErrorMapping.USER_NOT_FOUND.getCode())
                            .withErrorMessage(ErrorMapping.USER_NOT_FOUND.getMessage()).build());

            subscriber.onSuccess(currentUser);
        });
    }

    private User validateUserAccount(User user) {
        if (user.isAccountExpired()) {
            throw new BusinessExceptionBuilder()
                    .withErrorCode(ErrorMapping.ACCOUNT_EXPIRED.getCode())
                    .withErrorMessage(ErrorMapping.ACCOUNT_EXPIRED.getMessage())
                    .build();
        }

        if (!user.isEnable()) {
            throw new BusinessExceptionBuilder()
                    .withErrorCode(ErrorMapping.ACCOUNT_DISABLED.getCode())
                    .withErrorMessage(ErrorMapping.ACCOUNT_DISABLED.getMessage())
                    .build();
        }

        return user;
    }

    private User validateLogin(String rawPassword, User user) {
        if (!validatePasswordCredentials(rawPassword, user)) {
            int counterFailed = user.getCounterFailed() + 1;
            if (counterFailed >= authenticationProperties.getMaxLoginAttempt()) {
                user.setEnable(false);
            }

            user.setCounterFailed(counterFailed);
            user.setLastFailedLogin(new Date());
            userRepository.save(user);

            throw new BusinessExceptionBuilder()
                    .withErrorCode(ErrorMapping.INVALID_PASSWORD.getCode())
                    .withErrorMessage(ErrorMapping.INVALID_PASSWORD.getMessage())
                    .build();
        }

        user.setLastSuccessLogin(new Date());
        user.setCounterFailed(0);

        return userRepository.save(user);
    }

    private boolean validatePasswordCredentials(String rawPassword, User user) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public Single<Boolean> googleAuthentication() {
        return Single.create(subscriber -> {
            subscriber.onSuccess(true);
        });
    }

    @Override
    public Single<Boolean> facebookAuthentication() {
        return Single.create(subscriber -> {
            subscriber.onSuccess(true);
        });
    }
}
