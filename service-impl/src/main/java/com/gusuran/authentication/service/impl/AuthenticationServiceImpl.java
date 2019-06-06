package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.model.User;
import com.gusuran.authentication.model.exception.BusinessExceptionBuilder;
import com.gusuran.authentication.model.exception.ErrorMapping;
import com.gusuran.authentication.repository.api.UserRepository;
import com.gusuran.authentication.service.api.AuthenticationService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Single<User> normalAuthentication(String username, String rawPassword) {
        return Single.create(subscriber -> {

            User currentUser = userRepository
                    .findByUsername(username)
                    .map(user -> {
                        if (!validateLoginCredentials(rawPassword, user)) {
                            int counterFailed = user.getCounterFailed() + 1;
                            user.setCounterFailed(counterFailed);
                            user.setLastFailedLogin(new Date());

                            userRepository.save(user);

                            throw new BusinessExceptionBuilder()
                                    .withErrorCode(ErrorMapping.PASSWORD_IS_NOT_MATCH.getCode())
                                    .withErrorMessage(ErrorMapping.PASSWORD_IS_NOT_MATCH.getMessage())
                                    .build();
                        }

                        user.setLastSuccessLogin(new Date());

                        return userRepository.save(user);
                    })
                    .orElseThrow(() -> new BusinessExceptionBuilder()
                            .withErrorCode(ErrorMapping.USER_NOT_FOUND.getCode())
                            .withErrorMessage(ErrorMapping.USER_NOT_FOUND.getMessage()).build());

            subscriber.onSuccess(currentUser);
        });
    }

    private boolean validateLoginCredentials(String rawPassword, User user) {
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
