package com.example.dao.account_role;

import com.example.domain.account_role.AccountRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
@Repository("accountRoleDao")
public interface IAccountRoleDao {
    @Select("<script>SELECT  * FROM sys_account_role WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"accountId!=null and accountId!=''\">\n" +
            "AND ACCOUNTID=#{accountId}\n" +
            "</if>\n" +
            "<if test=\"roleId!=null and roleId!=''\">\n" +
            "AND ROLEID=#{roleId}\n" +
            "</if></script>")
    List<AccountRole> queryForObject(AccountRole accountRole);
    //为账户授予角色
    @Insert("<script>" +
            "insert into sys_account_role (id,accountId,roleId) values " +
            "<foreach collection='roleIds' item='roleId' separator=','>" +
            "(uuid(),#{accountId},#{roleId,jdbcType=VARCHAR})" +
            "</foreach>" +
            "</script>")
    int insertForObject(@Param("accountId") String accountId, @Param("roleIds") List<String> roleIds);
    @Insert("<script>" +
            "insert into sys_account_role (id,accountId,roleId) values " +
            "(#{id},#{accountId},#{roleId})" +
            "</script>")
    void insert1ForObject(AccountRole accountRole);
    //为账户移除角色
    @Delete("<script>" +
            "delete from sys_account_role where accountId=#{accountId}" +
            "</script>")
    int deleteForObject(@Param("accountId") String accountId);
}
