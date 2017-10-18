package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 */
public class ThreadTest {

    public static void main(String[] args) {


        Thread newth1 = new Thread(new Runnable() {
            @Override
            public  synchronized void run() {
                for(int i = 0;i<100;i++){
                    System.out.println(" --1sub1-- "+Thread.currentThread().getName()+" --1sub1-- "+i);
                }
            }
        });

        Thread newth2 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for(int i = 0;i<100;i++){
                    System.out.println(" --2sub2-- "+Thread.currentThread().getName()+" --2sub2-- "+i);
                }
            }
        });

        try {
            newth1.start();

            newth2.start();
           // newth.join();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void test1(){

    }
}
