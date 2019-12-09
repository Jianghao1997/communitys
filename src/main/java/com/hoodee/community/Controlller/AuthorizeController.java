package com.hoodee.community.Controlller;

import com.hoodee.community.dto.AccessTokenDTO;
import com.hoodee.community.dto.GithubUser;
import com.hoodee.community.mapper.UserMapper;
import com.hoodee.community.model.User;
import com.hoodee.community.provide.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 登录controller，根据GitHub api调用回调函数
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}")//读取配置文件的注解
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String rediretUrl;

    @GetMapping("/callback")//参数接收
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(rediretUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        //获取github token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //根据token获取GitHub用户名 以及一些相应的信息
        GithubUser githubuser = githubProvider.getUser(accessToken);
        if (githubuser != null ){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setName(githubuser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登陆成功 写cookie session
            response.addCookie(new Cookie("token",token));
            return "redirect:/";//重定向
        }else {
            //登录失败 重新登录
            return "redirect:/";//后续更改
        }
    }
}
