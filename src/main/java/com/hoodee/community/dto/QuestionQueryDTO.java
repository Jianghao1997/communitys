package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.service
 * Description：
 * Author: jianghao
 * Date:  2020.02.05 22:48
 * Modified By:
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
