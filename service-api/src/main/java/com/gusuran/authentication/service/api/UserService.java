package com.gusuran.authentication.service.api;

import com.gusuran.authentication.model.User;
import rx.Single;

public interface UserService {

    Single<Boolean> normalRegistration(String username, String password);

    Single<User> findUser(String username);
}
