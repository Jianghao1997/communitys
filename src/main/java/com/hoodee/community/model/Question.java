package com.hoodee.community.model;

import lombok.Data;

/**
 * Package: com.hoodee.community.model
 * Description：发起问题POJO
 * Author: jianghao
 * Date:  2019.12.10 23:22
 * Modified By:
 */
@Data
public class Question {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
