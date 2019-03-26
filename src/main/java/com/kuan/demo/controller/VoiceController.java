package com.kuan.demo.controller;

import com.kuan.demo.common.ServerResponse;
import com.kuan.demo.entity.Voice;
import com.kuan.demo.service.VoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/voice")
public class VoiceController {

    private static final AtomicInteger atomicInteger = new AtomicInteger();
    @Autowired
    private VoiceService voiceService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse add(@RequestParam(value = "uid")String uid, @RequestParam(value = "tid") String tid,@RequestParam(value = "region")String region,@RequestParam(value = "translation")String translation ,@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {  //添加语音
/*        System.out.println("文件类型"+ file.getContentType());
        System.out.println("文件内容"+ file.getBytes());
        file.transferTo(new File("D:\\录音.mp3"));*/
        Voice voice = new Voice();
        int num = atomicInteger.incrementAndGet();
        String str = String.format("%04d", num);
        voice.setRowKey(str);
        voice.setRegion(region);
        voice.setTranslation(translation);
        voice.setContent(file.getBytes());
        //byte [] b = {0x01,0x01};
        //voice.setContent(b);
        return  voiceService.putVoice(voice,uid,tid);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse get(@RequestParam(value = "vid") String vid,@RequestParam(value = "uid")String uid, @RequestParam(value = "tid") String tid) throws IOException {         //读取语音
        ServerResponse<Voice> response = voiceService.getVoice(vid,uid,tid);
/*        byte b[] = response.getData().getContent();
        String filepath = "D:\\语音.mp3";
        File file = new File(filepath);
        if(file.exists())
            file.delete();
        FileOutputStream f = new FileOutputStream(file);
        f.write(b,0,b.length);
        f.flush();
        f.close();*/
        return response;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse<List> voiceGet(@RequestParam (value = "id") String id) {
        List<Voice> list = new ArrayList<>();
        if(id.equals("1")){
            for(int i=0; i<10; i++){
                Voice voice = new Voice();
                //voice.setContent();
                list.add(voice);
            }
        }else if(id.equals("2")){
            for(int i=0; i<10; i++){
                Voice voice = new Voice();
                //voice.setContent();
                list.add(voice);
            }
        }

        return  new ServerResponse<List>(1,"success",list);
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public ServerResponse delete(@RequestParam(value = "vid") String vid,@RequestParam(value = "uid")String uid, @RequestParam(value = "tid") String tid) throws IOException {
       return voiceService.deleteVoice(vid,uid,tid);
    }

    @RequestMapping(value = "/my_voice", method = RequestMethod.GET)
    public ServerResponse<List> getMyvoice(@RequestParam(value = "uid") String uid) throws IOException {
        return voiceService.getMyVoice(uid);
    }

    @RequestMapping(value = "/topic_voice", method = RequestMethod.GET)
    public ServerResponse<List> getTopicVoice(@RequestParam(value = "tid")String tid) throws IOException {
        return voiceService.getTopicVoice(tid);
    }

    @RequestMapping(value = "voice_list", method = RequestMethod.GET)
    public ServerResponse<List> getVoices() throws IOException {
        return voiceService.getVoices();
    }
}
