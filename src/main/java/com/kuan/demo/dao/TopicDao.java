package com.kuan.demo.dao;

import com.kuan.demo.entity.Topic;

import java.io.IOException;
import java.util.List;

public interface TopicDao {
    //添加主题
    void putTopic(Topic topic, String uid) throws IOException;
    //获取主题
    Topic getTopic(String tid, String uid) throws IOException;
    //获取话题列表
    List<Topic> getTopics() throws IOException;
}
