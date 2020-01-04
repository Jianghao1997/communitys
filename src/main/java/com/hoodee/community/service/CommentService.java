package com.hoodee.community.service;

import com.hoodee.community.enums.CommentTypeEnum;
import com.hoodee.community.exception.CustomizeErrorCode;
import com.hoodee.community.exception.CustomizeException;
import com.hoodee.community.mapper.CommentMapper;
import com.hoodee.community.mapper.QuestionExtMapper;
import com.hoodee.community.mapper.QuestionMapper;
import com.hoodee.community.model.Comment;
import com.hoodee.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.hoodee.community.service
 * Description：
 * Author: wude
 * Date:  2020.01.04 9:44
 * Modified By:
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentid() == null || comment.getParentid() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentid());
            if (dbcomment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            commentMapper.insert(comment);
        } else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentid());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
