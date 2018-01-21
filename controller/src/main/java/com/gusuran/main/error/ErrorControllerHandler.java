package com.gusuran.main.error;

import com.gusuran.rest.web.model.base.BaseResponse;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface ErrorControllerHandler {

    Logger getLogger();

    MessageSource getMessageSource();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    default BaseResponse<Object> methodArgumentNotValidException(MethodArgumentNotValidException e){
        this.getLogger().warn(MethodArgumentNotValidException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(Errors.from(e.getBindingResult(), this.getMessageSource()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    default BaseResponse<Object> bindException(BindException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(Errors.from(e.getBindingResult(), this.getMessageSource()))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    default BaseResponse<Object> noHandlerFoundException(NoHandlerFoundException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    default BaseResponse<Object> throwable(Throwable e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    default BaseResponse<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .status(HttpStatus.METHOD_NOT_ALLOWED.name())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    default BaseResponse<Object> httpMessageReadableException(HttpMessageNotReadableException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    default BaseResponse<Object> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    default BaseResponse<Object> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e){
        this.getLogger().warn(BindException.class.getName(), e);

        return BaseResponse
                .builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE.name())
                .build();
    }
}
