package com.hoodee.community.service;

import com.hoodee.community.mapper.UserMapper;
import com.hoodee.community.model.User;
import com.hoodee.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 新建或者更新用户信息到数据库中
     * @param user
     */
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0){
          //新建
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else{
          //更新
            User dbuser = users.get(0);

            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
      }
    }
}
