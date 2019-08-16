package com.weijiang.service;

import com.weijiang.mapper.UserMapper;
import com.weijiang.model.User;
import com.weijiang.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrupdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User dbuser = users.get(0);
            User upuser = new User();
            upuser.setGmtModified(System.currentTimeMillis());
            upuser.setAvartaUrl(user.getAvartaUrl());
            upuser.setName(user.getName());
            upuser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(upuser, example);
        }
    }

}
