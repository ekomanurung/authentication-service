package com.gusuran.main.web.error;

import com.gusuran.rest.web.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    private HttpStatus httpStatus;
    private Object data;
    private ErrorResponse errors;

    public ResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResponseException(HttpStatus httpStatus, Object data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public ResponseException(HttpStatus httpStatus, ErrorResponse errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public ResponseException(Throwable cause, HttpStatus httpStatus, Object data) {
        super(cause);
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getData() {
        return data;
    }

    public ErrorResponse getErrors() {
        return errors;
    }
}