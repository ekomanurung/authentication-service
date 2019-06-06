package com.gusuran.authentication.model.exception;

public enum ErrorMapping {

    OK(200, "Ok..."),
    BAD_REQUEST(400, "Bad Request..."),
    USER_NOT_FOUND(404, "User not found..."),
    SAME_PASSWORD_OCCURED(500, "Invalid Password, Can't use same Password..."),
    INVALID_PASSWORD(401, "Can't Login, Invalid password"),
    ACCOUNT_EXPIRED(401, "Can't Login, Your Account already Expired"),
    ACCOUNT_DISABLED(401, "Can't Login, Your Account already disabled"),
    INTERNAL_SERVER_ERROR(500, "There is something wrong with the system");

    private int code;
    private String message;

    ErrorMapping(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
