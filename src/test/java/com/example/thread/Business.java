package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 */
public class Business {

    private static boolean flag = true;

    public synchronized  void sub(int i ){
        while (!flag){
            System.out.println(" sub while???");
            try {
                this.wait();
            }catch (InterruptedException e){

            }
        }
        for (int j = 0; j <5 ; j++) {
            System.out.println(Thread.currentThread().getName()+" --sub-- "+j+","+i+"/50");
        }
        flag = false;
        this.notify();
    }

    public synchronized void main(int i){
        while(flag){
            System.out.println(" main while???");
            try {
                this.wait();
            }catch (InterruptedException e){

            }
        }

       // System.out.println("wtf????");
        for (int j = 0; j < 10; j++) {
            System.out.println(Thread.currentThread().getName()+" --main-- "+j+","+i+"/50");
        }
        flag = true;
        this.notify();
    }
}
