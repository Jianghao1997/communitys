package com.hoodee.community.dto;

import lombok.Data;

/**
 * Package: com.hoodee.community.controlller
 * Description：文件传输对象
 * Author: jianghao
 * Date:  2020.02.05 17:18
 * Modified By:
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
