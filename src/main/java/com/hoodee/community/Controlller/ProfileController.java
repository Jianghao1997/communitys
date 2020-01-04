package com.hoodee.community.Controlller;

import com.hoodee.community.dto.PaginationDTO;
import com.hoodee.community.model.User;
import com.hoodee.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.hoodee.community.Controlller
 * Description：获取我的提问详情页
 * Author: jianghao
 * Date:  2019.12.22 11:58
 * Modified By:
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){
        // 通过session获取user对象 如果为空，则重定向到首页
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        // 通过请求的名字 实现左边表单标题动态修改
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        // 分页获取
        PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
