package com.gusuran.authentication.service.api;

import com.gusuran.authentication.model.User;
import rx.Single;

public interface AuthenticationService {

    Single<User> normalAuthentication(String username, String password);

    Single<Boolean> googleAuthentication();

    Single<Boolean> facebookAuthentication();
}
