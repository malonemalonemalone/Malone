package com.kuan.demo.controller;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Question;
import com.kuan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse add(@RequestParam(value = "title") String title, @RequestParam(value = "answer") String answer, @RequestParam(value = "uid") String uid) throws IOException, InterruptedException {
        int num = atomicInteger.incrementAndGet();
        String str = String.format("%04d", num);
        Question question = new Question();
        question.setRowKey(str);
        question.setTitle(title);
        question.setAnswer(answer);
        return questionService.putQuestion(question,uid);
}

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse get(@RequestParam(value = "qid") String qid, @RequestParam(value = "uid") String uid) throws IOException {
        ServerResponse<Question> response = questionService.getQuestion(qid, uid);
        return response;
    }
}
