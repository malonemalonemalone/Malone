package com.kuan.demo.service.Impl;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.dao.UserDao;
import com.kuan.demo.entity.User;
import com.kuan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    //添加用户
    public ServerResponse putUser(User user) throws IOException, InterruptedException{
        userDao.putUser(user);
        return ServerResponse.createBySuccess();
    }
    //获取用户信息
    public ServerResponse<User> getUser(String uid) throws IOException{
        return ServerResponse.createBySuccess("获取用户成功",userDao.getUser(uid));
    }
    //修改用户信息
    public ServerResponse updateInfo(String uid, String info, int operate) throws IOException{
        userDao.updateInfo(uid,info,operate);
        return ServerResponse.createBySuccess("修改成功");
    }
}
