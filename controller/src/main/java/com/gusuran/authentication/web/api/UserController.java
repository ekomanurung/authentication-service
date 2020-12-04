package com.gusuran.authentication.web.api;

import com.gusuran.authentication.configuration.mapper.BeanMapper;
import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.rest.web.model.request.ChangePasswordRequest;
import com.gusuran.authentication.rest.web.model.request.CreateUserRequest;
import com.gusuran.authentication.rest.web.model.response.UserResponse;
import com.gusuran.authentication.service.api.UserService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@RestController
@Api(value = "User Controller")
@RequestMapping("/api/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public DeferredResult<Response<UserResponse>> findUser(@PathVariable @Valid @NotBlank(message = "username must not be blank") String username) {

        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        userService.findUser(username)
                .map(response -> BeanMapper.map(response, UserResponse.class))
                .map(this::toResponse)
                .subscribe(result::setResult);

        return result;
    }

    @PostMapping("/registration")
    public DeferredResult<Response<Boolean>> createUser(@RequestBody @Valid CreateUserRequest request) {

        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        userService
                .normalRegistration(request.getUsername(), request.getPassword())
                .map(this::toResponse)
                .subscribe(result::setResult);

        return result;
    }

    @DeleteMapping("/{username}")
    public DeferredResult<Response<Boolean>> deleteUser(@PathVariable @Valid String username) {
        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        userService.deleteUser(username)
                .map(this::toResponse)
                .subscribe(result::setResult);

        return result;
    }

    @PostMapping("/change-password")
    public DeferredResult<Response<Boolean>> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        DeferredResult<Response<Boolean>> result = new DeferredResult<>();

        userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword())
                .map(this::toResponse)
                .subscribe(result::setResult);

        return result;
    }
}
