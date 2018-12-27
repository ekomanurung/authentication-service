package com.gusuran.authentication.web.api;

import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.web.error.ErrorHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@RestControllerAdvice
@Slf4j
public class ExceptionController extends AbstractController {

  private ErrorHelper errorHelper;

  public ExceptionController(ErrorHelper errorHelper) {
    this.errorHelper = errorHelper;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Response<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {

    return Response
        .builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.name())
        .errors(errorHelper.from(e.getBindingResult()))
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({BindException.class})
  public Response<Object> bindException(BindException e) {

    return Response
        .builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.name())
        .errors(errorHelper.from(e.getBindingResult()))
        .build();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NoHandlerFoundException.class})
  public Response<Object> noHandlerFoundException(NoHandlerFoundException e) {

    return Response
        .builder()
        .code(HttpStatus.NOT_FOUND.value())
        .message(HttpStatus.NOT_FOUND.name())
        .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Throwable.class})
  public Response<Object> throwable(Throwable e) {

    return Response
        .builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
        .build();
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public Response<Object> httpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {

    return Response
        .builder()
        .code(HttpStatus.METHOD_NOT_ALLOWED.value())
        .message(HttpStatus.METHOD_NOT_ALLOWED.name())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public Response<Object> httpMessageReadableException(HttpMessageNotReadableException e) {

    return Response
        .builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.name())
        .build();
  }

  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
  public Response<Object> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {

    return Response
        .builder()
        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
        .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())
        .build();
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
  public Response<Object> httpMediaTypeNotAcceptableException(
      HttpMediaTypeNotAcceptableException e) {

    return Response
        .builder()
        .code(HttpStatus.NOT_ACCEPTABLE.value())
        .message(HttpStatus.NOT_ACCEPTABLE.name())
        .build();
  }
}
