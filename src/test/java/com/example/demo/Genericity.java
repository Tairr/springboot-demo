package com.example.demo;

import com.example.domain.Teacher;
import com.example.domain.User;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tangjinhui on 2017/10/11. 泛型相关测试
 */
public class Genericity {

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
     //   test1();
     //   test2();

        test2();

        swap(new Integer[]{1,2},0,1);//编译通过
        swap(new String[]{"11","22"},0,1);

        //swap(new int[]{1,2},0,1);
        //编译不通过,因为int不是引用类型

        int a = biggerone(1,2);
        Number b = biggerone(1,2.0);
        Object c = biggerone(1,"2");
    }

    public static void test1(){

        ArrayList a  = new ArrayList<Integer>(); //t
        ArrayList<Integer> a1 = new ArrayList(); //t

        ArrayList a2 = new ArrayList<Integer>();
       a2.add(1);
        ArrayList<String> a3 = a2;
//        System.out.println(a3.get(0));   //false

        ArrayList<? extends Number> b = new ArrayList<Integer>();

        ArrayList<? super Integer> b1= new ArrayList<Number>();

    }

    /*利用反射获取方法参数的实际参数类型*/
    public static void test2() throws NoSuchMethodException {
        Method m = Genericity.class.getMethod("smap",Map.class);
        Type[] types = m.getGenericParameterTypes();
        System.out.println(types[0].getTypeName());

        ParameterizedType ptype = (ParameterizedType)types[0];
        System.out.println(ptype.getRawType());

        System.out.println(ptype.getActualTypeArguments()[0]);
        System.out.println(ptype.getActualTypeArguments()[1]);
    }


    public  static<T> void swap(T[]a,int i,int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        System.out.println(a);
    }

    public static<T> T biggerone(T x,T y){
        return y;
    }

    public static void smap(Map<Integer,String> m){

    }

    public  static void test3() throws ClassNotFoundException {
        Class A = Class.forName("com.example.domain.Teacher");
        Teacher b = new Teacher();
        try {
            User a = (User) A.newInstance();
            User b1 = (User)b;
            a.foo("t");
            b.foo("t");
        }catch (Exception e){

        }
    }
}
