package com.weijiang.exception;

public class CustomException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomException(ICustomErrorCode iCustomErrorCode) {
       this. message = iCustomErrorCode.getMessage();
       this. code = iCustomErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }
}
