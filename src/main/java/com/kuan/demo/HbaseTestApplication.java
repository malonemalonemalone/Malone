package com.kuan.demo;

import com.kuan.demo.Utils.HbaseTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HbaseTestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HbaseTestApplication.class, args);
       // HbaseTest hbaseTest = new HbaseTest();
       // hbaseTest.getAllTables();
        //hbaseTest.table();
	}

}
