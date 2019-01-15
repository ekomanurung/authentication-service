package com.gusuran.authentication.service.api;

import rx.Single;

public interface RegistrationService {

  Single<Boolean> normalRegistration(String username, String password);
}
