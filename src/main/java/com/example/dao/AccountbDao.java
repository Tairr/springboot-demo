package com.example.dao;

import com.example.domain.Accountb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tangjinhui on 2017/10/10.
 */
@Repository("accountbDao")
public interface AccountbDao {

    @Insert("<script>INSERT INTO accountb (id,name,password,money)" +
            " values (#{id},#{name},#{password},#{money})</script>")
    int add(Accountb account);

    //更新账户
    @Update("<script>UPDATE account set name=#{name},password=#{password},money=#{money} WHERE id=#{id}</script>")
    int update(Accountb account);

    int delete(int id);

    Accountb findAccountById(int id);

    List<Accountb> findAccountList();

    @Select("Select * from accountb where name = '${name}'")
    Accountb findAccountByName(@Param("name")String name);
}
