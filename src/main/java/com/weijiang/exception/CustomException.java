package com.weijiang.exception;

public class CustomException extends RuntimeException {
    private String message;

    public CustomException(ICustomErrorCode iCustomErrorCode) {
       this. message = iCustomErrorCode.getMessage();
    }
}
