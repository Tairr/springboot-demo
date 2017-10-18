package com.example.demo;

import java.lang.reflect.Method;

/**
 * Created by tangjinhui on 2017/10/11.
 */
public class AopAdviceImp implements AopAdvice {

    long beginTime = 0;
    @Override
    public void beforeMethod(Method m){

        System.out.println(m.getName()+" before at "+beginTime);
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void afterMethod(Method m){
        long end = System.currentTimeMillis();
        System.out.println(m.getName()+" cost total "+(end-beginTime));
    }
}
