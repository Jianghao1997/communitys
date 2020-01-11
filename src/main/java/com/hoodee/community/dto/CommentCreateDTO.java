package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：评论传输对象
 * Author: wude
 * Date:  2019.12.22 23:01
 * Modified By:
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
