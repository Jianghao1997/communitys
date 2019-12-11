package com.hoodee.community.Controlller;

import com.hoodee.community.mapper.UserMapper;
import com.hoodee.community.mapper.questionMapper;
import com.hoodee.community.model.Question;
import com.hoodee.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Package: com.hoodee.community.Controlller
 * Description：发起问题页controller
 * Author: jianghao
 * Date:  2019.12.09 23:32
 * Modified By:
 */
@Controller
public class PublishController {

    @Autowired
    questionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish (
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value ="description",required = false) String description,
            @RequestParam(value ="tag",required = false) String tag ,
            HttpServletRequest request, Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length !=0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        //获取保存时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = dateFormat.format(date);
        Long saveTime = Long.parseLong(str);
        
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(saveTime);
        question.setGmtModified(question.getGmtCreate());
        questionMapper.creat(question);
        return "redirect:/";//重定向
    }
}
