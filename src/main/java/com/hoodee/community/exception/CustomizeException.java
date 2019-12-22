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

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
