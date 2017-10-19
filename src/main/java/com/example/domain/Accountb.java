package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tangjinhui on 2017/10/10.
 */

@Entity
public class Accountb {

    @Id
    @GeneratedValue
    private int id ;
    private String name ;
    private String password;
    private double money;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
