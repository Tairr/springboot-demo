package com.example.dao;

import com.example.domain.Account;
import com.example.domain.base.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */
public interface  AccountDao {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();

    Account findAccountByName(String name);
}
