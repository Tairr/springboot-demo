package com.example.dao.account_user;

import com.example.domain.account.Account;
import com.example.domain.account_user.AccountUser;
import com.example.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
@Repository("accountUserDao")
public interface IAccountUserDao {
    @Select("<script>SELECT  * FROM sys_account_user WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"userId!=null and userId!=''\">\n" +
            "AND USERID=#{userId}\n" +
            "</if>\n" +
            "<if test=\"accountId!=null and accountId!=''\">\n" +
            "AND ACCOUNTID=#{accountId}\n" +
            "</if></script>")
    List<AccountUser> queryForObject(AccountUser accountUser);
    //新增账户-用户
    @Insert("<script>insert into sys_account_user (id,accountId,userId) values (#{id},#{accountId},#{userId})</script>")
    int insertForObject(AccountUser accountUser);
    //查询用户的账户信息
    @Select("<script>SELECT sa.* FROM sys_userinfo su " +
            "LEFT JOIN sys_account_user sau ON su.`ID`=sau.`USERID` " +
            "LEFT JOIN sys_account sa ON sau.`ACCOUNTID`=sa.`ID` " +
            "WHERE su.`ID`=#{id}</script>")
    List<Account> queryUserAccounts(String id);
    //查询账户的用户信息
    @Select("<script>select su.* from sys_userinfo su " +
            "left join sys_account_user sau on su.`ID`=sau.`USERID` " +
            "left join sys_account sa on sau.`ACCOUNTID`=sa.`ID` " +
            "where sa.`ID`=#{id}</script>")
    List<UserInfo> queryAccountUsers(String id);
    @Delete("<script>DELETE FROM sys_account_user WHERE userId=#{userId} </script>")
    void delete(AccountUser accountUser);
}
