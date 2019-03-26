package com.kuan.demo.service.Impl;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.dao.TopicDao;
import com.kuan.demo.entity.Topic;
import com.kuan.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicDao topicDao;

    public ServerResponse putTopic(Topic topic, String uid) throws IOException {
        topicDao.putTopic(topic, uid);
        return ServerResponse.createBySuccess("添加话题成功");
    }

    public ServerResponse<Topic> getTopic(String tid, String uid) throws IOException {
        Topic topic = topicDao.getTopic(tid, uid);
        return ServerResponse.createBySuccess(topic);
    }

    public ServerResponse<List> getTopics() throws IOException{
        return ServerResponse.createBySuccess("话题列表",topicDao.getTopics());
    }
}
