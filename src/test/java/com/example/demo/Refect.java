package com.example.demo;

import com.example.domain.Teacher;
import com.example.domain.User;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by tangjinhui on 2017/10/11.
 */
public class Refect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        //反射1
       // reflect1();

        //反射数组
        reflect2();

    }

    public static void reflect1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException, IllegalAccessException{

        User user = new User();
        //===反射
        // 获取class的3种方式
        System.out.println("1. "+User.class+">>"+user.getClass()+">>"+Class.forName("com.example.domain.User"));
        //
        Class cls[] = new Class[] { String.class,Integer.class };
        Constructor cons = User.class.getConstructor(cls);
        System.out.println("2. cons:"+cons);


        // getConstructor 和 newInstance
        Class cls2[] = new Class[] { String.class };
        Constructor c = String.class.getConstructor(cls2);
        User uu = (User)cons.newInstance("xm",18);
        System.out.println(c);

        Teacher t = new Teacher();
        t.getAge();


        //getMethod 和 invoke
        Method m = User.class.getMethod("foo",String.class);
        for(int i=0;i<10;i++){
            m.invoke(uu,Integer.toString(i));
        }

    }

    public static void reflect2(){

        int []a1 = new int[]{1,2};
        int a2[] = new int[]{5};
        int a3[][] = new int[3][2];

        System.out.println(a1.getClass());
        System.out.println(a2.getClass());
        System.out.println(a3.getClass());
        System.out.println(a1.getClass() == a2.getClass());

        System.out.println(a1.getClass().getSuperclass());
        System.out.println(a3.getClass().getSuperclass());
        System.out.println(a1.getClass().getSuperclass() == a3.getClass().getSuperclass());

        String str ="abc";
        System.out.println(Arrays.asList(a1));
        System.out.println(Arrays.asList(str));

    }
}
