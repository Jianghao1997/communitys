package com.hoodee.community.controlller;

import com.hoodee.community.dto.CommentDTO;
import com.hoodee.community.dto.QuestionDTO;
import com.hoodee.community.enums.CommentTypeEnum;
import com.hoodee.community.service.CommentService;
import com.hoodee.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Package: com.hoodee.community.controlller
 * Description：
 * Author: jianghao
 * Date:  2019.12.22 13:56
 * Modified By:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long  id,
                           Model model){
        QuestionDTO questionDTO = questionService.getByID(id);
        // 评论列表展示
        List<CommentDTO> comments =  commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
