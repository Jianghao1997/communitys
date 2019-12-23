package com.hoodee.community.Controlller;

import com.hoodee.community.dto.CommenDTO;
import com.hoodee.community.mapper.CommentMapper;
import com.hoodee.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Package: com.hoodee.community.Controlller
 * Description：回复Controller
 * Author: jianghao
 * Date:  2019.12.22 22:54
 * Modified By:
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommenDTO commenDTO){
        Comment comment = new Comment();
        comment.setParentid(commenDTO.getParentId());
        comment.setContent(commenDTO.getContent());
        comment.setType(commenDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikecount(0L);
        commentMapper.insert(comment);
        return null;
    }

}
