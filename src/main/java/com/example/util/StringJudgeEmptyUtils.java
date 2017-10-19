package com.example.util;


import com.example.domain.base.BaseModel;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断实体字段是否为空
 */
public class StringJudgeEmptyUtils {
    public static String StringJudgeEmpty(BaseModel baseModel, String[] strings) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        //拿到该类
       Class<?> clz = baseModel.getClass();
       //拿到父类
       Class<?> superClz = clz.getSuperclass();
       //获取实体类的所有属性，返回Field数组
        Field[] superFields = superClz.getDeclaredFields();
        for(Field superField : superFields){
            for(String s : strings){
                if(s.equals(superField.getName())){
                    //获取getter方法取得属性值
                    Method method = clz.getMethod("get" + getMethodName(superField.getName()));
                    String value = (String) method.invoke(baseModel);
                    if(StringUtils.isEmpty(value)){
                        resultMap.put("code","0001");
                        resultMap.put("data",s + " can not be null");
                        return gson.toJson(resultMap);
                    }
                }
            }
        }
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            for(String s : strings){
                if(s.equals(field.getName())){
                    //获取getter方法取得属性值
                    Method method = clz.getMethod("get" + getMethodName(field.getName()));
                    String value = (String) method.invoke(baseModel);
                    if(StringUtils.isEmpty(value)){
                        resultMap.put("code","0001");
                        resultMap.put("data",s + " can not be null");
                        return gson.toJson(resultMap);
                    }
                }
            }
        }
        return null;
    }
    // 把一个字符串的第一个字母大写
    private static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
