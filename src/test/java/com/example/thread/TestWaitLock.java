package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 * from : http://blog.csdn.net/u011784767/article/details/51426449
 */
public class TestWaitLock {

    private  int i = 10;

    private Object lock = new Object();

    public synchronized void  Method(){

        if(i>8){
            for (int j = 0; j <3 ; j++) {
                System.out.println(Thread.currentThread().getName()+" : "+i);
                i--;
            }

            try {
                System.out.println(Thread.currentThread().getName()+" wait begin ");
                lock.wait();
                System.out.println(Thread.currentThread().getName()+" wait end ");
            }catch (InterruptedException e){

            }
        }

        if(i<7 && i>1){

            for (int j = 0; j <7 ; j++) {
                System.out.println(Thread.currentThread().getName()+" : "+i);
                i--;
            }
            try {
                lock.notify();
                System.out.println(" after notify");
            }catch (Exception e){

            }
        }

    }

    public static void main(String[] args) {
        final TestWaitLock t = new TestWaitLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.Method();
            }
        },"A");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.Method();
            }
        },"B");

        t1.start();
        t2.start();


    }

}
