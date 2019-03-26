package com.kuan.demo.controller;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Topic;
import com.kuan.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse add(@RequestParam(value = "essay")String essay,@RequestParam(value = "uid")String uid) throws IOException {
        int num = atomicInteger.incrementAndGet();
        String str = String.format("%04d", num);
        Topic topic = new Topic();
        topic.setRowKey(str);
        topic.setEssay(essay);
        return topicService.putTopic(topic,uid);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse<Topic> get(@RequestParam(value = "tid") String tid,@RequestParam(value = "uid") String uid) throws IOException{
        ServerResponse<Topic> response = topicService.getTopic(tid,uid);
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse<List> getAll() throws IOException {
        return topicService.getTopics();
    }

}
