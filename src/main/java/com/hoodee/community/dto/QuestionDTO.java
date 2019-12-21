package com.hoodee.community.dto;

import com.hoodee.community.model.User;
import lombok.Data;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: wude
 * Date:  2019.12.21 22:47
 * Modified By:
 */
@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
