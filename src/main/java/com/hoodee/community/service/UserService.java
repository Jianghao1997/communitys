package com.hoodee.community.service;

import com.hoodee.community.mapper.UserMapper;
import com.hoodee.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.hoodee.community.service
 * Description：修复登录问题
 * Author: jianghao
 * Date:  2019.12.22 15:21
 * Modified By:
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbuser = userMapper.findByAccountId(user.getAccountId());
      if (dbuser == null){
          //新建插入
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else{
          //更新
          dbuser.setGmtModified(System.currentTimeMillis());
          dbuser.setAvatarUrl(user.getAvatarUrl());
          dbuser.setName(user.getName());
          dbuser.setToken(user.getToken());
          userMapper.update(dbuser);
      }
    }
}
