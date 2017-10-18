package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tangjinhui on 2017/10/13.
 */
public class Recevier {

    private static final Logger LOGGER = LoggerFactory.getLogger(Recevier.class);

    private CountDownLatch latch;

    @Autowired
    public Recevier(CountDownLatch latch){
        this.latch = latch;
    }

    public void receiveMessage(String message){
        LOGGER.info("Received <"+message+" >");
        latch.countDown();
    }

}
