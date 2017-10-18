package com.example.controller;


import com.example.domain.Teacher;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@RestController
public class HelloController {

    @Autowired
    private User user;

    @Value("${my.id}")
    private String id;

    @RequestMapping("/")
    public String hello() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException ,InstantiationException{

        //===反射
        // 获取class的3种方式
        System.out.println("1. "+User.class+">>"+user.getClass()+">>"+Class.forName("com.example.domain.User"));
        //
        Class cls[] = new Class[] { String.class,Integer.class };
        Constructor cons = User.class.getConstructor(cls);
        System.out.println("2. cons:"+cons);


        Class cls2[] = new Class[] { String.class };
        Constructor c = String.class.getConstructor(cls2);
        User uu = (User)cons.newInstance("xm",18);
        System.out.println(c);

        Teacher t = new Teacher();
        t.getAge();


        Method m = User.class.getMethod("foo",String.class);
        for(int i=0;i<10;i++){
            m.invoke(uu,Integer.toString(i));
        }


        return "Hello"+id;
    }


    @RequestMapping("/uer")
    public String getUser(){
        return user.getId()+">>"+user.getName()+">>"+user.getAge();
    }
}
