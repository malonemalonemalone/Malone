package com.kuan.demo.dao.daoImpl;

import com.kuan.demo.Utils.HBaseConnector;
import com.kuan.demo.dao.QuestionDao;
import com.kuan.demo.Utils.RowKeyCreator;
import com.kuan.demo.entity.Question;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("QuestionDao")
@Transactional
public class QuestionDaoImpl implements QuestionDao {

    private static final byte[] QF1_1 = Bytes.toBytes("title");
    private static final byte[] QF1_2 = Bytes.toBytes("answer");
    private static final byte[] CF2 = Bytes.toBytes("xxx");
    private static final byte[] QF2_1 = Bytes.toBytes("xxx");
    private static final byte[] CF1 = Bytes.toBytes("question");

    //@Autowired
    //添加题目
    @Override
    public void putQuestion(Question question, String uid) throws IOException, InterruptedException {
        Table table = HBaseConnector.getTable("pj:question_table");

        byte[] rowKey = RowKeyCreator.getQuestionKey(question.getRowKey(), uid);

        List<Row> batch = new ArrayList<>();
        Put put1 = new Put(rowKey);
        put1.addColumn(CF1, QF1_1, question.getTitle().getBytes());
        batch.add(put1);
        Put put2 = new Put(rowKey);
        put2.addColumn(CF1, QF1_2, question.getAnswer().getBytes());
        batch.add(put2);
        Object[] results = new Object[batch.size()];
        table.batch(batch, results);

        HBaseConnector.close();
    }

    //查询题目
    @Override
    public Question getQuestion(String qid, String uid) throws IOException {
        Table table = HBaseConnector.getTable("pj:question_table");

        Get get = new Get(RowKeyCreator.getQuestionKey(qid, uid));
        Result rs = table.get(get);
        Question question = new Question();
        question.setRowKey(qid);
        question.setTitle(Bytes.toString(rs.getValue(CF1, QF1_1)));
        question.setAnswer(Bytes.toString(rs.getValue(CF1, QF1_2)));

        HBaseConnector.close();
        return question;
    }
}
