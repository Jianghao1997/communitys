package com.hoodee.community.dto;

import lombok.Data;
/**
 * Package: com.hoodee.community.dto
 * Description：GitHub参数DTO
 * Author: jianghao
 * Date:  2019.12.10 23:18
 * Modified By:
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}
