package com.kuan.demo.Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseTest {
    private HBaseAdmin admin = null;

    private static Configuration configuration;
    public HbaseTest() throws Exception {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX2");
        configuration = HBaseConfiguration.create();
        //configuration.set("hbase.zookeeper.quorum","192.168.1.30");
        configuration.set("hbase.zookeeper.quorum","120.77.223.177");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        //configuration.set("hbase.master","192.168.1.30:60000");
        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY2");
        admin = new HBaseAdmin(configuration);
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ2");
    }

    public List getAllTables() {
        List<String> tables = null;
        if(admin != null) {
            try {
                HTableDescriptor[] allTable = admin.listTables();
                if (allTable.length > 0)
                    tables = new ArrayList<>();
                for (HTableDescriptor hTableDescriptor :allTable) {
                    System.out.println(hTableDescriptor.getNameAsString());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }
/*    public static void main(String[] args) throws Exception {
        HbaseTest hbaseTest = new HbaseTest();
        System.out.println("hehe");
        hbaseTest.getAllTables();
    }*/
    public void table() throws IOException {
        TableName name = TableName.valueOf("voice_table");
        boolean isE = admin.tableExists(name);
        System.out.println(isE);
    }
}
