package com.example.dao.role_authority;

import com.example.domain.role_authority.RoleAuthority;
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
@Repository("roleAuthorityDao")
public interface IRoleAuthorityDao {
    @Select("<script>SELECT  * FROM sys_role_authority WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"roleId!=null and roleId!=''\">\n" +
            "AND ROLEID=#{roleId}\n" +
            "</if></script>")
    List<RoleAuthority> queryForObject(RoleAuthority roleAuthority);

    @Delete("<script>" +
            "DELETE FROM sys_role_authority WHERE 1=1" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"authorityId!=null and authorityId!=''\">\n" +
            "AND AUTHORITYID=#{authorityId}\n" +
            "</if>\n" +
            "<if test=\"roleId!=null and roleId!=''\">\n" +
            "AND ROLEID=#{roleId}\n" +
            "</if>\n" +
            "</script>")
    int deleteObject(RoleAuthority roleAuthority);

    @Insert("<script>INSERT INTO sys_role_authority (ID,AUTHORITYID,ROLEID) VALUES (#{id},#{authorityId},#{roleId})</script>")
    void addForObject(RoleAuthority roleAuthority);
}
