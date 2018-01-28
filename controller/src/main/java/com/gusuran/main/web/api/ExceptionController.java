package com.gusuran.main.web.api;

import com.gusuran.main.web.error.ErrorControllerHandler;
import com.gusuran.main.web.error.ResponseException;
import com.gusuran.properties.ErrorsProperties;
import com.gusuran.rest.web.model.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApiIgnore
@RestControllerAdvice
public class ExceptionController extends AbstractController implements ErrorControllerHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ErrorsProperties errorsProperties;

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<BaseResponse<Object>> responseException(ResponseException e){
        BaseResponse response = new BaseResponse();
        response.setData(e.getData());
        if(e.getErrors() != null){
            response.setErrors(e.getErrors().getErrors());
            translateMessage(response.getErrors());
        }

        if(response.getErrors() != null && response.getErrors().containsKey("system")){
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }else {
            response.setCode(e.getHttpStatus().value());
            response.setStatus(e.getHttpStatus().name());

            return ResponseEntity.status(e.getHttpStatus()).body(response);
        }
    }

    private void translateMessage(Map<String, List<String>> errors){
        errors.forEach((key, value) -> {
            List<String> transformMessages = value
                    .stream()
                    .map(message -> {
                        String messageMapping = errorsProperties.getMapping().get(message);
                        if(messageMapping == null){
                            return message;
                        }else{
                            return messageMapping;
                        }
                    }).collect(Collectors.toList());

            errors.put(key, transformMessages);
        });
    }
}