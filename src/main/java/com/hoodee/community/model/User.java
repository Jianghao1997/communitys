package com.hoodee.community.model;

import lombok.Data;

/**
 * Package: com.hoodee.community.model
 * Description：user用户POJO
 * Author: jianghao
 * Date:  2019.12.09 21:09
 * Modified By:
 */
@Data
public class User {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;

}
