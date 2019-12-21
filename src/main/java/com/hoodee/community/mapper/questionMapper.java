package com.hoodee.community.mapper;

import com.hoodee.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
public interface questionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},)")
    void creat(Question question);

    @Select("select * from question")
    List<Question> list();
}
