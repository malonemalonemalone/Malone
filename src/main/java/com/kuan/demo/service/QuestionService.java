package com.kuan.demo.service;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Question;

import java.io.IOException;

public interface QuestionService {
    //添加问题
    ServerResponse putQuestion(Question question, String uid) throws IOException, InterruptedException;
    //查询问题
    ServerResponse<Question> getQuestion(String qid, String uid) throws IOException;
}
