package com.example.dao.orgtree;

import com.example.domain.orgtree.EncoderOrgTree;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/5/5.
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
@Repository("orgTreeDao")
public interface IOrgTreeDao {
    @Insert("<script>INSERT INTO sys_orgtree " +
            "(ID, ORGID, CHILDRENID) VALUES " +
            "  (#{id},#{orgId},#{childrenId})</script>")
    int insert(Map<String, String> map);


    @Select("<script>SELECT  * FROM sys_orgtree WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"orgId!=null and orgId!=''\">\n" +
            "AND ORGID=#{orgId}\n" +
            "</if>\n" +
            "<if test=\"childrenId!=null and childrenId!=''\">\n" +
            "AND CHILDRENID=#{childrenId}\n" +
            "</if>\n" +
            "</script>")
    List<Map<String, String>> queryForList(Map<String, String> map);
    //删除关联，因为删除的是没有儿子的节点
    @Delete("<script>DELETE FROM sys_orgtree WHERE CHILDRENID=#{id}</script>")
    int delete(String id);
    @Select("<script>SELECT  * FROM tbl_res_attr WHERE ResType='113' </script>")
    List<EncoderOrgTree> queryForList_EncoderOrgTree();
}
