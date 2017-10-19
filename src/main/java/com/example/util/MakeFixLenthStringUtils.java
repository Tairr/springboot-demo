package com.example.util;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/17.
 */
public class MakeFixLenthStringUtils {
    public static String getFixLenthString(int strLen){
        Random rm = new Random();
        //获取随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10,strLen);
        //将获取的随机数转换成字符串
        String fixLenthString = String.valueOf(pross);
        //返回固定长度的随机数
        return fixLenthString.substring(1,strLen + 1);
    }
}
