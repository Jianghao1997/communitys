package com.hoodee.community.mapper;

import com.hoodee.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}