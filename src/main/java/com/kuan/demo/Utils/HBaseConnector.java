package com.kuan.demo.Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * HBase连接辅助类
 */
public class HBaseConnector {
    private static final String QUORUM = "120.77.223.177";
    private static final String PORT = "2181";
    private static Configuration conf;
    private static Connection conn;
    private static Admin admin;
    private static Table table;

    //建立连接
    private static void init() {
        getConfiguration();
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭连接
    public static void close() {
        try {
            if(table != null){
                table.close();
            }
            if(admin != null) {
                admin.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取conf
    private static void getConfiguration() {
        if(conf == null) {
            conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", QUORUM);
            conf.set("hbase.zookeeper.property.clientPort", PORT);
        }
    }

    //获取HBaseAdmin实例
    public static Admin getAdmin() {
        init();
        try {
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admin;
    }

    //获取HTable实例
    public static Table getTable(String tableName) {
        init();
        try {
            table = conn.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }
}
