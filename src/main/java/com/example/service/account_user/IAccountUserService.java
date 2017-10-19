package com.example.service.account_user;



import com.example.domain.account.Account;
import com.example.domain.account_user.AccountUser;
import com.example.domain.userinfo.UserInfo;

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
public interface IAccountUserService {
    List<AccountUser> queryForObject(AccountUser accountUser);
    //新增账户-用户
    int insertForObject(AccountUser accountUser);
    //查询用户的账户信息
    List<Account> queryUserAccounts(String id);
    //查询账户的用户信息
    List<UserInfo> queryAccountUsers(String id);
    void delete(AccountUser accountUser);
}
