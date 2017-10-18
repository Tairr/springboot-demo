package com.example.demo;

import java.lang.reflect.Method;

/**
 * Created by tangjinhui on 2017/10/11.
 */
public interface AopAdvice {
    void beforeMethod(Method m);
    void afterMethod(Method m);
}
