package com.hoodee.community.mapper;

import com.hoodee.community.dto.QuestionDTO;
import com.hoodee.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.hoodee.community.mapper
 * Description：发起问题页Mapper
 * Author: jianghao
 * Date:  2019.12.10 23:18
 * Modified By:
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},)")
    void creat(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserID(@Param(value = "userId") Long userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserID(@Param(value = "userId") Long userId);

    @Select("select * from question where id = #{id}")
    Question getByID(@Param(value = "id") Long id);

    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmtModified},tag = #{tag} where id = #{id}")
    void update(Question question);
}
