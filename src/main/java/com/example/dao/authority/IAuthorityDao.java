package com.example.dao.authority;


import com.example.domain.authority.Authority;
import com.example.domain.base.BaseModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
@Repository("authorityDao")
public interface IAuthorityDao {
    @Select("<script>SELECT  * FROM sys_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityName!=null and authorityName!=''\">\n" +
            "AND AUTHORITYNAME=#{authorityName}\n" +
            "</if>\n" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    List<Authority> queryForObject(BaseModel baseModel);
    @Insert("<script>INSERT INTO sys_authority " +
            "(ID, AUTHORITYNAME, DESCRIPTION, CREATETIME, UPDATETIME, IFUSE, ORDERNUM) VALUES " +
            "  (#{id},#{authorityName},#{description},#{createTime},#{updateTime},#{ifuse},#{orderNum})</script>")
    int addObject(BaseModel baseModel);

    @Delete("<script>DELETE FROM sys_authority WHERE ID=#{id}</script>")
    int deleteObject(BaseModel baseModel);
    @Update("<script>UPDATE sys_authority  set AUTHORITYNAME=#{authorityName},DESCRIPTION=#{description},ORDERNUM=#{orderNum} WHERE ID=#{id}</script>")
    void updateForObject(BaseModel baseModel);
}
