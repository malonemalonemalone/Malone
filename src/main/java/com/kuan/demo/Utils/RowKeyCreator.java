package com.kuan.demo.Utils;

import org.apache.hadoop.hbase.util.Bytes;

public class RowKeyCreator {

    //用户行键
    public static byte[] getUserKey(String uid) {
        byte prefix = (byte) (Long.hashCode(Long.valueOf(uid)) % 10);
        byte[] rowKey = Bytes.add(Bytes.toBytes(prefix), uid.getBytes());
        return rowKey;
    }
    //话题行键
    public static byte[] getTopicKey(String tid, String uid) {
        byte prefix = (byte) (Long.hashCode(Long.valueOf(tid)) % 10);
        byte[] rowKey = Bytes.add(Bytes.toBytes(prefix), uid.getBytes(), tid.getBytes());
        return rowKey;
    }
    //语音行键
    public static byte[] getVoiceKey(String vid, String uid, String tid) {
        byte[] rowKey;

        if(tid == null) {
            byte prefix = (byte) (Long.hashCode(Long.valueOf(vid)) % 10);
            rowKey = Bytes.add(Bytes.toBytes(prefix), Bytes.add(Bytes.toBytes("0000"), uid.getBytes(), vid.getBytes()));
        }
        else {
            byte prefix = (byte) (Long.hashCode(Long.valueOf(vid)) % 10);
            rowKey = Bytes.add(Bytes.toBytes(prefix), Bytes.add(tid.getBytes(), uid.getBytes(), vid.getBytes()));
        }
        return rowKey;
    }
    //问题行键
    public static byte[] getQuestionKey(String qid, String uid) {
        byte prefix = (byte) (Long.hashCode(Long.valueOf(qid)) % 10);
        byte[] rowKey = Bytes.add(Bytes.toBytes(prefix), uid.getBytes(), qid.getBytes());
        return rowKey;
    }
}
