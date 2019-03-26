package com.kuan.demo.service;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Topic;

import java.io.IOException;
import java.util.List;

public interface TopicService {
    //添加主题
    ServerResponse putTopic(Topic topic, String uid) throws IOException;
    //获取主题
    ServerResponse<Topic> getTopic(String tid, String uid) throws IOException;

    ServerResponse<List> getTopics() throws IOException;
}
