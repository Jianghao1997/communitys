package com.hoodee.community.enums;

import com.hoodee.community.model.Comment;

/**
 * Package: com.hoodee.community.enums
 * Description：
 * Author: jianghao
 * Date:  2020.01.04 9:41
 * Modified By:
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }
    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }

}
