package com.hoodee.community.mapper;

import com.hoodee.community.model.Question;
import com.hoodee.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
}