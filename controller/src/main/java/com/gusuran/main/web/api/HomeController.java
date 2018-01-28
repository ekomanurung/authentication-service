package com.gusuran.main.web.api;

import com.gusuran.rest.web.model.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Api(value = "Home Controller")
@RequestMapping("/home")
public class HomeController extends AbstractController{

    @GetMapping
    public DeferredResult<BaseResponse> greetings(String message){

        DeferredResult<BaseResponse> result = new DeferredResult<>();

        result
                .setResult(BaseResponse
                .builder()
                        .code(200)
                        .status(HttpStatus.OK.name())
                        .data("test")
                        .build());

        return result;
    }
}
