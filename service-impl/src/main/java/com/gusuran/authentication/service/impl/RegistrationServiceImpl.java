package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.model.RegistrationMethod;
import com.gusuran.authentication.model.User;
import com.gusuran.authentication.model.UserBuilder;
import com.gusuran.authentication.repository.api.UserRepository;
import com.gusuran.authentication.service.api.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.Date;

@Service
public class RegistrationServiceImpl implements RegistrationService {

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
}
