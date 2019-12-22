package com.hoodee.community.advice;

import com.hoodee.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.hoodee.community.advice
 * Description：错误处理页面
 * Author: jianghao
 * Date:  2019.12.22 20:53
 * Modified By:
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){
        if (ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        } else {
            model.addAttribute("message","服务太热了，要不然稍等下再来试试!!!");
        }
        return new ModelAndView("error");
    }

}
