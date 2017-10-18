package com.example.demo;

import org.aopalliance.aop.Advice;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by tangjinhui on 2017/10/11. 代理及Aop例
 */
public class AopTemp {

    public static void main(String[] args) {

//        Class proxyClazz =  Proxy.getProxyClass(Collection.class.getClassLoader(),Collection.class);
//        System.out.println(proxyClazz);
//        printConstructor(proxyClazz);
//        printMethod(proxyClazz);
//        createProxyInstance();
         Collection proxy3 = (Collection) getProxyt(new ArrayList<>(),new AopAdviceImp());
         proxy3.add("aa1");
         proxy3.add("bb1");
        System.out.println(proxy3.size());
    }


    //打印构造方法
    public  static  void printConstructor(Class clazz){
        Constructor[] cons =  clazz.getConstructors();
        System.out.println(ExecutabelList(cons));
    }

    //打印成员方法
    public  static  void printMethod(Class clazz){
        Method[] mes = clazz.getMethods();
        System.out.println(ExecutabelList(mes));

    }

    /**
     * 获取要打印的列表数据
     * 每行一个方法,按照func(arg1,arg2)的格式
     * @param executables
     * @return
     */
    public  static String  ExecutabelList(Executable[] ab){


        StringBuilder sb = new StringBuilder();
        for(Executable a : ab){
            String name = a.getName();
            sb.append(name);
            sb.append("(");
            Class[] classParams = a.getParameterTypes();
            for(Class c : classParams){
                sb.append(c.getName()).append(",");
            }
            if(classParams !=null && classParams.length != 0){
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(")\n");

        }

        return sb.toString();
    }


    /**
     * 测试创建实例对象
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public  static  void createProxyInstance() {

        /**
         * 方法1：先创建代理类，再使用反射创建实例对象
         */
        Class clazz1 = Proxy.getProxyClass(Collection.class.getClassLoader(),Collection.class);
        try {

            Constructor con = clazz1.getConstructor(InvocationHandler.class);
            Collection  proxy1 = (Collection)con.newInstance(new InvocationHandler(){
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            });
        }catch (Exception e){

        }


        /**
         * 方法2：直接使用newProxyInstance方法创建实例对象
         */

        Collection proxy2 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(), new Class[]{Collection.class}, new InvocationHandler() {
            ArrayList ass = new ArrayList();
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(" before invoke method :"+method.getName());
                if(args != null)
                    System.out.println(" args class: "+args.length);
                return method.invoke(ass,args);
            }
        });
        proxy2.add("aaa");
        proxy2.add("bbb");
        proxy2.isEmpty();
        System.out.println(proxy2.size());
        System.out.println(proxy2.getClass().getName());
    }


    public static  Object getProxyt(final Object target, final AopAdviceImp advice){
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                advice.beforeMethod(method);
                Object reval = method.invoke(target,args);
                advice.afterMethod(method);
                return reval;
            }
        });
        return proxy;
    }

}
