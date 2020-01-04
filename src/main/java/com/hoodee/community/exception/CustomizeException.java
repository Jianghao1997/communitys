package com.hoodee.community.exception;

/**
 * Package: com.hoodee.community.exception
 * Description：
 * Author: jianghao
 * Date:  2019.12.22 21:18
 * Modified By:
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getcode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
