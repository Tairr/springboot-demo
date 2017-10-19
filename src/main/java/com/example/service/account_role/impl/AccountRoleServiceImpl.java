package com.example.service.account_role.impl;


import com.example.dao.account_role.IAccountRoleDao;
import com.example.domain.account_role.AccountRole;
import com.example.service.account_role.IAccountRoleService;
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
@Service("userRoleService")
public class AccountRoleServiceImpl implements IAccountRoleService {
    @Autowired
    private IAccountRoleDao userRoleDao;

    @Override
    public List<AccountRole> queryForObject(AccountRole accountRole) {
        return this.userRoleDao.queryForObject(accountRole);
    }
    //为账户授予角色
    @Override
    public int insertForObject(String accountId,List<String> roleIds){
        return this.userRoleDao.insertForObject(accountId,roleIds);
    }
    //为账户移除角色
    public int deleteForObject(String accountId){
        return this.userRoleDao.deleteForObject(accountId);
    }
}
