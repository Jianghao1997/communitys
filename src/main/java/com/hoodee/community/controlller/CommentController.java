package com.hoodee.community.controlller;

import com.hoodee.community.dto.CommentCreateDTO;
import com.hoodee.community.dto.CommentDTO;
import com.hoodee.community.dto.ResultDTO;
import com.hoodee.community.enums.CommentTypeEnum;
import com.hoodee.community.exception.CustomizeErrorCode;
import com.hoodee.community.model.Comment;
import com.hoodee.community.model.User;
import com.hoodee.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Package: com.hoodee.community.controlller
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
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        // 判断回复内容是否为空
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
