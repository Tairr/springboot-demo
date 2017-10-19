package com.example.service.account_user.impl;


import com.example.dao.account_user.IAccountUserDao;
import com.example.domain.account.Account;
import com.example.domain.account_user.AccountUser;
import com.example.domain.userinfo.UserInfo;
import com.example.service.account_user.IAccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/1/12.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@Service("accountUserService")
public class AccountUserServiceImpl implements IAccountUserService {
    @Autowired
    private IAccountUserDao accountUserDao;

    @Override
    public List<AccountUser> queryForObject(AccountUser accountUser) {
        return this.accountUserDao.queryForObject(accountUser);
    }
    @Override
    public int insertForObject(AccountUser accountUser) {
        return this.accountUserDao.insertForObject(accountUser);
    }

    @Override
    public List<Account> queryUserAccounts(String id) {
        return this.accountUserDao.queryUserAccounts(id);
    }

    @Override
    public List<UserInfo> queryAccountUsers(String id) {
        return this.accountUserDao.queryAccountUsers(id);
    }

    @Override
    public void delete(AccountUser accountUser) {
        this.accountUserDao.delete(accountUser);
    }

}
