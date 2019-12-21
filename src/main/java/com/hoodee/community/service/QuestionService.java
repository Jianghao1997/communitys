package com.hoodee.community.service;

import com.hoodee.community.dto.QuestionDTO;
import com.hoodee.community.mapper.UserMapper;
import com.hoodee.community.mapper.questionMapper;
import com.hoodee.community.model.Question;
import com.hoodee.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.hoodee.community.service
 * Description：
 * Author: jianghao
 * Date:  2019.12.21 22:48
 * Modified By:
 */
@Service
public class QuestionService {

    @Autowired
    private questionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    // 未分页查询
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList =new ArrayList<>();
        for (Question question : questions){
         User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);// question中的对象拷贝到questionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
