package com.example.service;

import com.example.domain.Accountb;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */
public interface IAccountbService {

    int add(Accountb account);

    int update(Accountb account);

    int delete(int id);

    Accountb findAccountById(int id);

    List<Accountb> findAccountList();

    Accountb findAccountByName(String name);




}
