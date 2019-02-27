package com.oeynet.dev.mockserver;


import com.oeynet.dev.mockserver.coms.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileNotFoundException;

@SpringBootApplication
@EnableScheduling
public class MockServerApplication {

    @Autowired
    private Trade trade;

    public static void main(String[] args)  {

        //启动服务器
        SpringApplication.run(MockServerApplication.class, args);

    }

}

