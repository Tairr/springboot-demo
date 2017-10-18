package com.example.domain;

import javax.persistence.Id;

/**
 * Created by tangjinhui on 2017/10/10.
 */
public class Customer {

    @Id
    public Long  id;

    public String firstName;
    public String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
