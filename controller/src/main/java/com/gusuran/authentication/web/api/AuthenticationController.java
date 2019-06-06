package com.gusuran.authentication.web.api;

import com.gusuran.authentication.configuration.mapper.BeanMapper;
import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.rest.web.model.request.AuthenticationRequest;
import com.gusuran.authentication.rest.web.model.response.UserResponse;
import com.gusuran.authentication.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController extends AbstractController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("_normal")
    public DeferredResult<Response<UserResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        authenticationService.normalAuthentication(request.getUsername(), request.getPassword())
                .map(user -> BeanMapper.map(user, UserResponse.class))
                .map(this::toResponse)
                .subscribe(value -> result.setResult(value));

        return result;
    }

    @PostMapping("_google")
    public DeferredResult<Response<UserResponse>> googleAuthentication(@RequestBody @Valid AuthenticationRequest request) {
        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        return result;
    }

    @PostMapping("_facebook")
    public DeferredResult<Response<UserResponse>> facebookAuthentication(@RequestBody @Valid AuthenticationRequest request) {
        DeferredResult<Response<UserResponse>> result = new DeferredResult<>();

        return result;
    }
}
