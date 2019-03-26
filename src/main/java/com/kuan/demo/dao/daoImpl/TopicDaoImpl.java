package com.kuan.demo.dao.daoImpl;

import com.kuan.demo.Utils.HBaseConnector;
import com.kuan.demo.Utils.RowKeyCreator;
import com.kuan.demo.dao.TopicDao;
import com.kuan.demo.entity.Topic;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("TopicDao")
@Transactional
public class TopicDaoImpl implements TopicDao {

    private static final byte[] CF1 = Bytes.toBytes("info");
    private static final byte[] QF1_1 = Bytes.toBytes("essay");

    //@Autowired
    //添加主题
    @Override
    public void putTopic(Topic topic, String uid) throws IOException {
        Table table = HBaseConnector.getTable("pj:topic_table");

        byte[] rowKey = RowKeyCreator.getTopicKey(topic.getRowKey(), uid);

        Put put = new Put(rowKey);
        put.addColumn(CF1, QF1_1, topic.getEssay().getBytes());
        table.put(put);

        HBaseConnector.close();
    }

    //获取主题
    @Override
    public Topic getTopic(String tid, String uid) throws IOException {
        Table table = HBaseConnector.getTable("pj:topic_table");

        Get get = new Get(RowKeyCreator.getTopicKey(tid, uid));
        Result rs = table.get(get);
        Topic topic = new Topic();
        topic.setRowKey(tid);
        topic.setEssay(Bytes.toString(rs.getValue(CF1, QF1_1)));

        HBaseConnector.close();
        return topic;
    }

    //获取话题列表
    @Override
    public List<Topic> getTopics() throws IOException {
        Table table = HBaseConnector.getTable("pj:topic_table");

        byte[] startKey = RowKeyCreator.getTopicKey("0001", "0001");
        Filter filter = new PageFilter(10L);
        Scan scan = new Scan(startKey, filter);
        ResultScanner rs = table.getScanner(scan);

        List<Topic> topics = new ArrayList<>();

        for(Result r : rs) {
            Topic topic = new Topic();
            topic.setRowKey(Bytes.toString(r.getRow()));
            topic.setEssay(Bytes.toString(r.getValue(CF1, QF1_1)));
            topics.add(topic);
        }
        rs.close();
        HBaseConnector.close();
        return topics;
    }
}
