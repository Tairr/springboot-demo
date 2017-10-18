package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 * from : http://blog.csdn.net/u011784767/article/details/51426449
 */
public class TestWaitLock2 {

    public static void main(String[] args) {
        int i = 10;
        TestLockClass tc = new TestLockClass();

        new Thread(new Runnable() {
            @Override
            public void run() {
                tc.Method1(i);
            }
        },"A").start();


        tc.Method2(i);

    }

}
