package com.kuan.demo.dao.daoImpl;

import com.kuan.demo.Utils.HBaseConnector;
import com.kuan.demo.Utils.RowKeyCreator;
import com.kuan.demo.dao.UserDao;
import com.kuan.demo.entity.User;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    private static final byte[] CF1 = Bytes.toBytes("info");
    private static final byte[] QF1_1 = Bytes.toBytes("password");
    private static final byte[] QF1_2 = Bytes.toBytes("nickname");
    private static final byte[] QF1_3 = Bytes.toBytes("sex");
    private static final byte[] QF1_4 = Bytes.toBytes("area");

    //@Autowired
    //添加用户
    @Override
    public String putUser(User user) throws IOException, InterruptedException {
        Table table = HBaseConnector.getTable("pj:user_table");

        byte[] rowKey = RowKeyCreator.getUserKey(user.getRowKey());

        List<Row> batch = new ArrayList<>();
        Put put1 = new Put(rowKey);
        put1.addColumn(CF1, QF1_1, user.getPassword().getBytes());
        batch.add(put1);
        Put put2 = new Put(rowKey);
        put2.addColumn(CF1, QF1_2, user.getNickname().getBytes());
        batch.add(put2);
        Put put3 = new Put(rowKey);
        put2.addColumn(CF1, QF1_3, user.getSex().getBytes());
        batch.add(put3);
        Put put4 = new Put(rowKey);
        put2.addColumn(CF1, QF1_4, user.getArea().getBytes());
        batch.add(put4);
        Object[] results = new Object[batch.size()];
        table.batch(batch, results);

        HBaseConnector.close();
        return user.getRowKey();
    }

    //获取用户信息
    @Override
    public User getUser(String uid) throws IOException {
        Table table = HBaseConnector.getTable("pj:user_table");

        Get get = new Get(RowKeyCreator.getUserKey(uid));
        Result rs = table.get(get);
        User user = new User();
        user.setRowKey(uid);
        user.setPassword(Bytes.toString(rs.getValue(CF1, QF1_1)));
        user.setNickname(Bytes.toString(rs.getValue(CF1, QF1_2)));
        user.setSex(Bytes.toString(rs.getValue(CF1, QF1_3)));
        user.setArea(Bytes.toString(rs.getValue(CF1, QF1_4)));

        HBaseConnector.close();
        return user;
    }

    //修改信息
    @Override
    public void updateInfo(String uid, String info, int operate) throws IOException {
        Table table = HBaseConnector.getTable("pj:user_table");

        Put put = new Put(RowKeyCreator.getUserKey(uid));
        switch (operate) {
            case 1:
                put.addColumn(CF1, QF1_1, info.getBytes());
                break;
            case 2:
                put.addColumn(CF1, QF1_2, info.getBytes());
                break;
            case 3:
                put.addColumn(CF1, QF1_3, info.getBytes());
                break;
            case 4:
                put.addColumn(CF1, QF1_4, info.getBytes());
                break;
            default:
                System.out.println("请传入正确的操作码!!");
                break;
        }
        table.put(put);

        HBaseConnector.close();
    }
}
