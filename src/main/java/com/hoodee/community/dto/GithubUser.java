package com.hoodee.community.dto;

import lombok.Data;
/**
 * Package: com.hoodee.community.dto
 * Description：获取GitHub上用户的POJO
 * Author: jianghao
 * Date:  2019.12.10 23:18
 * Modified By:
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
