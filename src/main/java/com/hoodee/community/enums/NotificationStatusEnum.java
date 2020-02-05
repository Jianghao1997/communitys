package com.hoodee.community.enums;

/**
 * Package: com.hoodee.community.enums
 * Description：
 * Author: jianghao
 * Date:  2020.02.05 13:42
 * Modified By:
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
