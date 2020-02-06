package com.hoodee.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Package: com.hoodee.community.dto
 * Description：标签传输对象
 * Author: jianghao
 * Date:  2020.02.04 15:35
 * Modified By:
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
