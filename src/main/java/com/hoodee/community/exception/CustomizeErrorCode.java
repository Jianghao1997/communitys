package com.hoodee.community.exception;

/**
 * Package: com.hoodee.community.exception
 * Description：自定义错误请求
 * Author: jianghao
 * Date:  2019.12.22 21:28
 * Modified By:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在了，要不要换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
