package com.example.service;

import com.example.dao.AccountDao;
import com.example.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountDao accountDAO;
    @Override
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }

    @Override
    public Account findAccountByName(String name){
        return accountDAO.findAccountByName(name);
    }


}
