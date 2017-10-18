package com.example.domain;

/**
 * Created by tangjinhui on 2017/10/11.
 */
public class Teacher extends User {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  void foo(String name){
        System.out.println(this.getName()+" Hello Teacher "+name);
    }
}
