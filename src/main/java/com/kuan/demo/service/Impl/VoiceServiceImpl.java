package com.kuan.demo.service.Impl;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.dao.VoiceDao;
import com.kuan.demo.entity.Voice;
import com.kuan.demo.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    private VoiceDao voiceDao;

    public ServerResponse putVoice(Voice voice,String uid, String tid) throws IOException, InterruptedException {
        voiceDao.putVoice(voice,uid,tid);
        return ServerResponse.createBySuccess("添加录音成功");
    }

    public ServerResponse<Voice> getVoice(String vid, String uid, String tid) throws IOException {
        Voice voice = voiceDao.getVoice(vid,uid,tid);
        return ServerResponse.createBySuccess(voice);
    }

    public ServerResponse deleteVoice(String vid, String uid, String tid) throws IOException {
        voiceDao.deleteVoice(vid,uid,tid);
        return ServerResponse.createBySuccess("删除录音成功");
    }

    //我的配音
    public ServerResponse<List> getMyVoice(String uid) throws IOException{
        return ServerResponse.createBySuccess("我的配音",voiceDao.getMyVoice(uid));
    }
    //话题的配音
    public ServerResponse<List> getTopicVoice(String tid) throws IOException{
        return ServerResponse.createBySuccess("话题配音",voiceDao.getTopicVoice(tid));
    }

    public ServerResponse<List> getVoices() throws IOException{
        return ServerResponse.createBySuccess("配音列表",voiceDao.getVoices());
    }
}
