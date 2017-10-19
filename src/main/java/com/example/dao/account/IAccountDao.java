package com.example.dao.account;


import com.example.domain.account.Account;
import com.example.domain.account_role.AccountRole;
import com.example.domain.authority.Authority;
import com.example.domain.base.BaseModel;
import com.example.domain.role.Role;
import com.example.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by dell on 2017/1/11.
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
@Repository("accountDao")
public interface IAccountDao {

    @Select("<script>SELECT  * FROM sys_account WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"username!=null and username!=''\">\n" +
            "AND USERNAME=#{username}\n" +
            "</if>\n" +
            "<if test=\"password!=null and password!=''\">\n" +
            "AND PASSWORD=#{password}\n" +
            "</if>\n" +
            "<if test=\"appId!=null and appId!=''\">\n" +
            "AND APPID=#{appId}\n" +
            "</if>\n" +
            "<if test=\"communicationId!=null and communicationId!=''\">\n" +
            "AND COMMUNICATIONID=#{communicationId}\n" +
            "</if>\n" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    default List<Account> queryForObject(BaseModel baseModel) {
        return null;
    }

    @Select("SELECT * FROM sys_account")
    List<Account> queryForAllObject(BaseModel baseModel);

    @Select("<script>SELECT  * FROM v_account_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ACCOUNTID=#{id}\n" +
            "</if>\n" +
            "<if test=\"token!=null and token!=''\">\n" +
            "AND TOKEN=#{token}\n" +
            "</if>\n" +
            "<if test=\"appId!=null and appId!=''\">\n" +
            "AND APP=#{appId}\n" +
            "</if>\n" +
            "<if test=\"communicationId!=null and communicationId!=''\">\n" +
            "AND COMMUNICATIONID=#{communicationId}\n" +
            "</if>\n" +
            "<if test=\"username!=null and username!=''\">\n" +
            "AND USERNAME=#{username}\n" +
            "</if></script>")
    List<Authority> queryForAuthorities(BaseModel baseModel);
    //根据账户id查询用户信息
    @Select("<script>SELECT su.* FROM sys_userinfo su " +
            "LEFT JOIN sys_account_user sau ON su.`ID`=sau.`USERID` " +
            "LEFT JOIN sys_account sa ON sau.`ACCOUNTID`=sa.`ID` " +
            "WHERE sa.`ID`=#{id}</script>")
    List<UserInfo> queryForUserInfoByAccount(BaseModel baseModel);
    //插入账户
    @Insert("<script>INSERT INTO sys_account (id,username,password,salt,createtime,updatetime,ifuse,ordernum,token,appId,communicationId)" +
            " values (#{id},#{username},#{password},#{salt},#{createTime},#{updateTime},#{ifuse},#{orderNum},#{token},#{appId},#{communicationId})</script>")
    int insertForObject(BaseModel baseModel);
    //更新账户
    @Update("<script>UPDATE sys_account set username=#{username},password=#{password},salt=#{salt},createtime=#{createTime}," +
            "updatetime=#{updateTime},ifuse=#{ifuse},ordernum=#{orderNum},token=#{token},appId=#{appId},communicationId=#{communicationId} WHERE id=#{id}</script>")
    int updateForObject(BaseModel baseModel);
    //删除账户
    @Delete("<script>DELETE FROM sys_account WHERE id=#{id}</script>")
    int deleteForObject(BaseModel baseModel);
    @Update("<script>UPDATE sys_account set password=#{password},salt=#{salt},appId=#{appId} WHERE username=#{username}</script>")
    int update1ForObject(Account account);

    @Select("<script>SELECT * FROM sys_account_role WHERE accountId=#{id} </script>")
    List<AccountRole> queryForAccountRole(String id);
    @Select("<script>SELECT * FROM sys_role WHERE id=#{id} </script>")
    List<Role> queryForRole(String id);
    @Update("<script>UPDATE sys_role set ROLENAME=#{roleName} WHERE ID=#{id} </script>")
    void updateForRole(BaseModel baseModel);
    @Select("<script>SELECT * FROM sys_role WHERE ROLENAME=#{roleName} </script>")
    List<Role> queryNameForRole(BaseModel baseModel);
    @Update("<script>UPDATE sys_account_role set ROLEID=#{roleId} WHERE ID=#{id} </script>")
    void  update1ForRole(AccountRole accountRole);
    @Select("<script>SELECT * FROM account WHERE accountId=#{id} </script>")
    Account findAccountByName(String id);
}
