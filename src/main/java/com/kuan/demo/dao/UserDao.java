package com.kuan.demo.dao;

import com.kuan.demo.entity.User;

import java.io.IOException;

public interface UserDao {
    //添加用户
    String putUser(User user) throws IOException, InterruptedException;
    //获取用户信息
    User getUser(String uid) throws IOException;
    //修改用户信息
    void updateInfo(String uid, String info, int operate) throws IOException;
}
