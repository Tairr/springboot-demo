package com.example.service;

import com.example.dao.AccountbDao;
import com.example.domain.Accountb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */

@Service
public class AccountbServiceImpl implements IAccountbService {

    @Autowired
    AccountbDao accountDAO;
    @Override
    public int add(Accountb account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(Accountb account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Accountb findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Accountb> findAccountList() {
        return accountDAO.findAccountList();
    }

    @Override
    public Accountb findAccountByName(String name){
        return accountDAO.findAccountByName(name);
    }


}
