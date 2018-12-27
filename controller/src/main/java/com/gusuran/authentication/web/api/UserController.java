package com.gusuran.authentication.web.api;

import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.rest.web.model.request.AuthenticationRequest;
import com.gusuran.authentication.rest.web.model.request.ChangePasswordRequest;
import com.gusuran.authentication.rest.web.model.request.CreateUserRequest;
import com.gusuran.authentication.rest.web.model.response.UserResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;

import javax.validation.Valid;

@RestController
@Api(value = "User Controller")
@RequestMapping("/api/user")
public class UserController extends AbstractController {

  @GetMapping("/{username}")
  public Single<Response<UserResponse>> findUser(@PathVariable @Valid String username) {
    return Single.just(toResponse(UserResponse.builder().build()));
  }

  @PostMapping
  public Single<Response<Boolean>> createUser(@RequestBody @Valid CreateUserRequest request) {
    return Single.just(toResponse(true));
  }

  @DeleteMapping("/{username}")
  public Single<Response<Boolean>> deleteUser(@PathVariable @Valid String username) {
    return Single.just(toResponse(true));
  }

  @PostMapping("/_auth")
  public Single<Response<UserResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
    return Single.just(toResponse(UserResponse.builder().build()));
  }

  @PostMapping("/change-password")
  public Single<Response<Boolean>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
    return Single.just(toResponse(true));
  }
}
