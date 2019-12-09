package com.hoodee.community.mapper;

import com.hoodee.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Package: com.hoodee.community.mapper
 * Description：
 * Author: jianghao
 * Date:  2019.12.09 21:25
 * Modified By:
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,bio) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
