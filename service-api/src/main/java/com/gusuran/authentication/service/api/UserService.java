package com.gusuran.authentication.service.api;

import com.gusuran.authentication.model.User;
import rx.Single;

public interface UserService {

    Single<Boolean> normalRegistration(String username, String password);

    Single<User> findUser(String username);

    Single<Boolean> deleteUser(String username);

    Single<Boolean> changePassword(String username, String oldPassword, String newPassword);
}
