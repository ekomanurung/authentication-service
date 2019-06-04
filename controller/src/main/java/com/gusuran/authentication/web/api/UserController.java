package com.gusuran.authentication.web.api;

import com.gusuran.authentication.configuration.mapper.BeanMapper;
import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.rest.web.model.request.AuthenticationRequest;
import com.gusuran.authentication.rest.web.model.request.ChangePasswordRequest;
import com.gusuran.authentication.rest.web.model.request.CreateUserRequest;
import com.gusuran.authentication.rest.web.model.response.UserResponse;
import com.gusuran.authentication.service.api.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@RestController
@Api(value = "User Controller")
@RequestMapping("/api/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public DeferredResult<Response<UserResponse>> findUser(@PathVariable @Valid String username) {

        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        userService.findUser(username)
                .map(response -> BeanMapper.map(response, UserResponse.class))
                .map(response -> toResponse(response))
                .subscribe(value -> result.setResult(value));

        return result;
    }

    @PostMapping("/registration")
    public DeferredResult<Response<Boolean>> createUser(@RequestBody @Valid CreateUserRequest request) {

        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        userService
                .normalRegistration(request.getUsername(), request.getPassword())
                .map(response -> toResponse(response)).subscribe(value -> result.setResult(value));

        return result;
    }

    @DeleteMapping("/{username}")
    public DeferredResult<Response<Boolean>> deleteUser(@PathVariable @Valid String username) {
        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        userService.deleteUser(username)
                .map(response -> toResponse(response))
                .subscribe(value -> result.setResult(value));

        return result;
    }

    @PostMapping("/_auth")
    public DeferredResult<Response<UserResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        return result;
    }

    @PostMapping("/change-password")
    public DeferredResult<Response<Boolean>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        return result;
    }
}
