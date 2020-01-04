package com.hoodee.community.Controlller;

import com.hoodee.community.dto.QuestionDTO;
import com.hoodee.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Package: com.hoodee.community.Controlller
 * Description：
 * Author: jianghao
 * Date:  2019.12.22 13:56
 * Modified By:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String  id,
                           Model model){
        Long questionId = null;
        questionId = Long.parseLong(id);
        QuestionDTO questionDTO = questionService.getByID(questionId);
        // 累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
