package com.hoodee.community.mapper;

import com.hoodee.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Package: com.hoodee.community.mapper
 * Description：用户Mapper
 * Author: jianghao
 * Date:  2019.12.09 21:25
 * Modified By:
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
