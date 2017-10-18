package com.example.thread;

/**
 * Created by tangjinhui on 2017/10/12.
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Business bs = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <50 ; i++) {
                    bs.sub(i);
                }
            }
        }).start();

        for (int i = 0; i <50 ; i++) {
            bs.main(i);
        }
    }

}
