package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: wude
 * Date:  2019.12.22 23:01
 * Modified By:
 */
@Data
public class CommenDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
