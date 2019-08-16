package com.weijiang.exception;

public enum CustomErrorCode implements ICustomErrorCode{

    QUESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？");

    private String message;

    CustomErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
