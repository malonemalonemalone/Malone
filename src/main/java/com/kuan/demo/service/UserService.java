package com.kuan.demo.service;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.User;

import java.io.IOException;

public interface UserService {
    //添加用户
    ServerResponse putUser(User user) throws IOException, InterruptedException;
    //获取用户信息
    ServerResponse<User> getUser(String uid) throws IOException;
    //修改用户信息
    ServerResponse updateInfo(String uid, String info, int operate) throws IOException;
}
