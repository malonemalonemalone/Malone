package com.kuan.demo.dao;

import com.kuan.demo.entity.Voice;

import java.io.IOException;
import java.util.List;

public interface VoiceDao {
    //添加语音
    void putVoice(Voice voice, String uid, String tid) throws IOException, InterruptedException;
    //读取语音
    Voice getVoice(String vid, String uid, String tid) throws IOException;
    //删除语音
    void deleteVoice(String vid, String uid, String tid) throws IOException;
    //我的配音
    List<Voice> getMyVoice(String uid) throws IOException;
    //话题的配音
    List<Voice> getTopicVoice(String tid) throws IOException;
    //保护方言列表
    List<Voice> getVoices() throws IOException;
}
