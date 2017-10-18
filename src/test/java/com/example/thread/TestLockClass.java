package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 */
public class TestLockClass {

    public void  Method1(int i ){
        while (i<8 && i>3){
            System.out.println(" Method1 while ???");
            try {
                this.wait();
            }catch (InterruptedException e){

            }
        }
        for (int j = 0; j < 3; j++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            i--;
        }
        System.out.println(i+" not go m1 while ???");
        this.notify();

    }

    public void  Method2(int i){
        while (i>7 || i<3){
            System.out.println(" Method2 while ???");
            try {
                this.wait();
            }catch (InterruptedException e){

            }
        }
        for (int j = 0; j <7 ; j++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            i--;
        }
        this.notify();
    }

}
