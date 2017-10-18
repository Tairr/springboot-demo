package com.example.dao.impl;

import com.example.domain.Customer;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */
public interface  CustomerRepository  {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
