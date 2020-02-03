package com.hoodee.community.exception;

/**
 * Package: com.hoodee.community.exception
 * Description：自定义错误请求
 * Author: jianghao
 * Date:  2019.12.22 21:28
 * Modified By:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试！"),
    SYS_ERROR(2004,"服务太热了，要不然稍等下再来试试!!!"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在!"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在!"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空!"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getcode() {
        return code;
    }
    private Integer code;
    private String message;


    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }
}
