package com.example.dao.role;


import com.example.domain.base.BaseModel;
import com.example.domain.role.Role;
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
@Repository("roleDao")
public interface IRoleDao {
    @Select("<script>SELECT  * FROM sys_role WHERE ifuse!='n'\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"roleName!=null and roleName!=''\">\n" +
            "AND ROLENAME=#{roleName}\n" +
            "</if>\n" +
            "</script>")
    List<Role> queryForObject(BaseModel baseModel);

    @Insert("<script>INSERT INTO sys_role " +
            "(ID, ROLENAME, DESCRIPTION, CREATETIME, UPDATETIME, IFUSE, ORDERNUM) VALUES " +
            "  (#{id},#{roleName},#{description},#{createTime},#{updateTime},#{ifuse},#{orderNum})</script>")
    int addObject(BaseModel baseModel);

    @Delete("<script>DELETE FROM sys_role WHERE ID=#{id}</script>")
    int deleteObject(BaseModel baseModel);
    @Update("<script>UPDATE sys_role set ROLENAME=#{roleName},DESCRIPTION=#{description},ORDERNUM=#{orderNum} WHERE ID=#{id}</script>")
    void updateRole(BaseModel baseModel);

}
