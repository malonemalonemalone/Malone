package com.kuan.demo.service.Impl;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.dao.QuestionDao;
import com.kuan.demo.entity.Question;
import com.kuan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionDao questionDao;

    public ServerResponse<Question> putQuestion(Question question, String uid) throws IOException, InterruptedException {
        questionDao.putQuestion(question,uid);
        return ServerResponse.createBySuccessMessage("添加问题成功");
    }

    public ServerResponse<Question> getQuestion(String qid, String uid) throws IOException {
        Question question = questionDao.getQuestion(qid, uid);
        return ServerResponse.createBySuccess(question);
    }
}
