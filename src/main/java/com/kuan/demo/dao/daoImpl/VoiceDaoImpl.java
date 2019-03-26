package com.kuan.demo.dao.daoImpl;

import com.kuan.demo.Utils.HBaseConnector;
import com.kuan.demo.Utils.RowKeyCreator;
import com.kuan.demo.dao.VoiceDao;
import com.kuan.demo.entity.Voice;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("voiceDao")
@Transactional
public class VoiceDaoImpl implements VoiceDao {

    private static final byte[] CF1 = Bytes.toBytes("info");
    private static final byte[] QF1_1 = Bytes.toBytes("region");
    private static final byte[] QF1_2 = Bytes.toBytes("translation");
    private static final byte[] CF2 = Bytes.toBytes("content");
    private static final byte[] QF2_1 = Bytes.toBytes("content");

    //@Autowired
    //添加语音
    @Override
    public void putVoice(Voice voice, String uid, String tid) throws IOException, InterruptedException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        byte[] rowKey = RowKeyCreator.getVoiceKey(voice.getRowKey(), uid, tid);

        List<Row> batch = new ArrayList<>();
        Put put1 = new Put(rowKey);
        put1.addColumn(CF1, QF1_1, voice.getRegion().getBytes());
        batch.add(put1);
        Put put2 = new Put(rowKey);
        put2.addColumn(CF1, QF1_2, voice.getTranslation().getBytes());
        batch.add(put2);
        Put put3 = new Put(rowKey);
        put3.addColumn(CF2, QF2_1, voice.getContent());
        batch.add(put3);
        Object[] results = new Object[batch.size()];
        table.batch(batch, results);

        HBaseConnector.close();
    }

    /**
     * 通过rowKey查询语音
     * @param vid 行键
     * @return 语音实体
     */
    @Override
    public Voice getVoice(String vid, String uid, String tid) throws IOException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        Get get = new Get(RowKeyCreator.getVoiceKey(vid, uid, tid));
        Result rs = table.get(get);
        Voice voice = new Voice();
        voice.setRowKey(vid);
        voice.setRegion(Bytes.toString(rs.getValue(CF1, QF1_1)));
        voice.setTranslation(Bytes.toString(rs.getValue(CF1, QF1_2)));
        voice.setContent(rs.getValue(CF2, QF2_1));

        HBaseConnector.close();
        return voice;
    }

    //通过rowKey删除语音
    @Override
    public void deleteVoice(String vid, String uid, String tid) throws IOException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        Delete del = new Delete(RowKeyCreator.getVoiceKey(vid, uid, tid));
        table.delete(del);

        HBaseConnector.close();
    }

    //保护方言列表
    @Override
    public List<Voice> getVoices() throws IOException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        byte[] startRow = RowKeyCreator.getVoiceKey("0001", "0001", "0001");
        Filter filter = new PageFilter(10L);
        Scan scan = new Scan(startRow, filter);
        ResultScanner rs = table.getScanner(scan);
        List<Voice> voices = listVoices(rs);

        rs.close();
        HBaseConnector.close();
        return voices;
    }

    //我的配音
    @Override
    public List<Voice> getMyVoice(String uid) throws IOException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        byte[] startRow = Bytes.add(Bytes.toBytes("0000"), uid.getBytes());
        byte[] endRow = Bytes.add(Bytes.toBytes("0000"), Bytes.toBytes(Integer.valueOf(uid) + 1));
        Scan scan = new Scan(startRow, endRow);
        ResultScanner rs = table.getScanner(scan);
        List<Voice> voices = listVoices(rs);

        rs.close();
        HBaseConnector.close();
        return voices;
    }

    //话题的配音
    @Override
    public List<Voice> getTopicVoice(String tid) throws IOException {
        Table table = HBaseConnector.getTable("pj:voice_table");

        byte[] startRow = tid.getBytes();
        byte[] endRow = Bytes.toBytes(Integer.valueOf(tid) + 1);
        Scan scan = new Scan(startRow, endRow);
        ResultScanner rs = table.getScanner(scan);
        List<Voice> voices = listVoices(rs);

        rs.close();
        HBaseConnector.close();
        return voices;
    }

    //语音列表装配
    private List<Voice> listVoices(ResultScanner rs) {
        List<Voice> voices = new ArrayList<>();

        for(Result r : rs) {
            Voice voice = new Voice();
            voice.setRegion(Bytes.toString(r.getValue(CF1, QF1_1)));
            voice.setTranslation(Bytes.toString(r.getValue(CF1, QF1_2)));
            voice.setContent(r.getValue(CF2, QF2_1));
            voices.add(voice);
        }
        return voices;
    }
}
