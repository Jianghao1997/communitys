package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: jianghao
 * Date:  2020.02.05 14:10
 * Modified By:
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
