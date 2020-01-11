package com.hoodee.community.dto;

import com.hoodee.community.model.User;
import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: jianghao
 * Date:  2020.01.11 21:23
 * Modified By:
 */
@Data
public class CommentDTO {

    private Long id;
    private Long parentid;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likecount;
    private String content;
    private User user;
}
