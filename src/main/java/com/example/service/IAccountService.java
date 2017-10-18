package com.example.service;

import com.example.domain.Account;
import com.example.domain.base.BaseModel;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */
public interface IAccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();

    Account findAccountByName(String name);




}
