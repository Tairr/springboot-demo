package com.example.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by tangjinhui on 2017/10/12.
 * from  https://github.com/brianway/java-learning/blob/master/blogs/javase/java%E5%9F%BA%E7%A1%80%E5%B7%A9%E5%9B%BA%E7%AC%94%E8%AE%B0(5)-%E5%A4%9A%E7%BA%BF%E7%A8%8B%E4%B9%8B%E5%85%B1%E4%BA%AB%E6%95%B0%E6%8D%AE.md
 */
public class ThreadScopeShareData {
    //准备一个哈希表，为每个线程准备数据
    private  static Map<Thread,Integer> threadData = new HashMap<>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            int data = new Random().nextInt();
                            threadData.put(Thread.currentThread(),data);
                            System.out.println(Thread.currentThread()+" put data："+data);
                            new A().get();
                            new B().get();
                        }
                    }).start();
        }
    }
    static  class A{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from "+Thread.currentThread()+" get data "+data);
        }
    }

    static  class B{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from "+Thread.currentThread()+" get data "+data);
        }
    }
}
