package com.hoodee.community.service;

import com.hoodee.community.dto.CommentDTO;
import com.hoodee.community.enums.CommentTypeEnum;
import com.hoodee.community.enums.NotificationStatusEnum;
import com.hoodee.community.enums.NotificationTypeEnum;
import com.hoodee.community.exception.CustomizeErrorCode;
import com.hoodee.community.exception.CustomizeException;
import com.hoodee.community.mapper.*;
import com.hoodee.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 回复/评论插入
     * @param comment
     * @param commentator
     */
    @Transactional // 事务注解
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbcomment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(dbcomment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            commentMapper.insert(comment);
            // 增加提问数
            Comment parenComment = new Comment();
            parenComment.setId(comment.getParentId());
            parenComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parenComment);
            // 创建评论通知
            createNotify(comment, dbcomment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        } else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            // 创建回复通知
            createNotify(comment,question.getCreator(), commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    /**
     * 创建通知
     * @param comment
     * @param receiver
     * @param outerTitle
     * @param notifierName
     * @param notificationType
     * @param outerId
     */
    private void createNotify(Comment comment, Long receiver, String outerTitle, String notifierName, NotificationTypeEnum notificationType, Long outerId) {
        // 如果同一人回复则直接return
        if (receiver == comment.getCommentator()){
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 查询评论回复列表
     * @param id
     * @param type
     * @return
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0){
            return new ArrayList<>();
        }
        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);

        // 获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
