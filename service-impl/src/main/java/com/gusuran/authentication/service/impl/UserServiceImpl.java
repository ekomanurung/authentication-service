package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.model.RegistrationMethod;
import com.gusuran.authentication.model.User;
import com.gusuran.authentication.model.UserBuilder;
import com.gusuran.authentication.model.exception.BusinessExceptionBuilder;
import com.gusuran.authentication.model.exception.ErrorMapping;
import com.gusuran.authentication.repository.api.UserRepository;
import com.gusuran.authentication.service.api.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

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

    @Override
    public Single<Boolean> deleteUser(String username) {
        return Single.create(subscriber -> {
            Long updatedRow = userRepository.deleteByUsername(username);

            subscriber.onSuccess(updatedRow > 0);
        });
    }

    @Override
    public Single<Boolean> changePassword(String username, String oldPassword, String newPassword) {
        return Single.create(subscriber -> {
            if (StringUtils.equals(oldPassword, newPassword)) {
                throw new BusinessExceptionBuilder()
                        .withErrorCode(ErrorMapping.SAME_PASSWORD_OCCURED.getCode())
                        .withErrorMessage(ErrorMapping.SAME_PASSWORD_OCCURED.getMessage())
                        .build();
            } else {
                Boolean response = userRepository
                        .findByUsername(username)
                        .map(user -> {
                            String newPasswordEncoded = passwordEncoder.encode(newPassword);

                            user.setPassword(newPasswordEncoded);
                            return userRepository.save(user) != null;
                        })
                        .orElseThrow(() -> new BusinessExceptionBuilder()
                                .withErrorCode(ErrorMapping.USER_NOT_FOUND.getCode())
                                .withErrorMessage(ErrorMapping.USER_NOT_FOUND.getMessage())
                                .build());

                subscriber.onSuccess(response);
            }
        });
    }
}
