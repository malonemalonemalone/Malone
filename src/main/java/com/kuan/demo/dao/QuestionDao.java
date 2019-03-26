package com.kuan.demo.dao;

import com.kuan.demo.entity.Question;

import java.io.IOException;

public interface QuestionDao {
    //添加问题
    void putQuestion(Question question, String uid) throws IOException, InterruptedException;
    //查询问题
    Question getQuestion(String qid, String uid) throws IOException;
}
