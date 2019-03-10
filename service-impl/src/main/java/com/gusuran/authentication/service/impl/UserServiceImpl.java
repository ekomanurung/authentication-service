package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.model.RegistrationMethod;
import com.gusuran.authentication.model.User;
import com.gusuran.authentication.model.UserBuilder;
import com.gusuran.authentication.model.exception.BusinessException;
import com.gusuran.authentication.model.exception.BusinessExceptionBuilder;
import com.gusuran.authentication.model.exception.ErrorMapping;
import com.gusuran.authentication.repository.api.UserRepository;
import com.gusuran.authentication.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Single<Boolean> normalRegistration(String username, String password) {
        return Single.create(subscriber -> {
            User user = new UserBuilder()
                    .withActive(false)
                    .withEnable(true)
                    .withCounterFailed(0)
                    .withRegistrationMethod(RegistrationMethod.NORMAL)
                    .withUsername(username)
                    .withPassword(passwordEncoder.encode(password))
                    .withLastFailedLogin(null)
                    .withLastSuccessLogin(null)
                    .withCreatedDate(new Date())
                    .build();

            User savedUser = userRepository.save(user);

            subscriber.onSuccess(savedUser != null);
        });
    }

    @Override
    public Single<User> findUser(String username) {
        return Single.create(subscriber -> {

            User user = userRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new BusinessExceptionBuilder()
                            .withErrorCode(ErrorMapping.USER_NOT_FOUND.getCode())
                            .withErrorMessage(ErrorMapping.USER_NOT_FOUND.getMessage())
                            .build());

            subscriber.onSuccess(user);
        });
    }
}
