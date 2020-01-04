package com.hoodee.community.Controlller;

import com.hoodee.community.dto.CommentDTO;
import com.hoodee.community.dto.ResultDTO;
import com.hoodee.community.exception.CustomizeErrorCode;
import com.hoodee.community.model.Comment;
import com.hoodee.community.model.User;
import com.hoodee.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private CommentService commentService;
    /*
    * @RequestBody注解可以解析json格式的参数
    * 获取前台传来的commenDTO对象的值并自动解析
    * 存到数据库当中
    */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentid(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1L);
        comment.setLikecount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

}
