package com.kuan.demo.service;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Voice;

import java.io.IOException;
import java.util.List;

public interface VoiceService {
    //添加语音
    ServerResponse putVoice(Voice voice,String uid, String tid) throws IOException, InterruptedException;
    //读取语音
    ServerResponse<Voice> getVoice(String vid, String uid, String tid) throws IOException;
    //删除语音
    ServerResponse deleteVoice(String vid, String uid, String tid) throws IOException;
    //我的配音
    ServerResponse<List> getMyVoice(String uid) throws IOException;
    //话题的配音
    ServerResponse<List> getTopicVoice(String tid) throws IOException;

    ServerResponse<List> getVoices() throws IOException;
}
