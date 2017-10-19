package com.example.dao.resource;


import com.example.domain.base.BaseModel;
import com.example.domain.resource.Resource;
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
@Repository("resourceDao")
public interface IResourceDao {
    @Select("<script>SELECT  * FROM sys_resource WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"resourceName!=null and resourceName!=''\">\n" +
            "AND RESOURCENAME=#{resourceName}\n" +
            "</if>\n" +
            "<if test=\"type!=null and type!=''\">\n" +
            "AND TYPE=#{type}\n" +
            "</if>\n" +
            "<if test=\"parentId!=null and parentId!=''\">\n" +
            "AND PARENTID=#{parentId}\n" +
            "</if>\n" +
            "<if test=\"parentName!=null and parentName!=''\">\n" +
            "AND PARENTNAME=#{parentName}\n" +
            "</if>\n" +
            "<if test=\"appName!=null and appName!=''\">\n" +
            "AND APPNAME=#{appName}\n" +
            "</if>\n" +
            "<if test=\"url!=null and url!=''\">\n" +
            "AND URL=#{url}\n" +
            "</if>\n" +
            "<if test=\"level!=null and level!=''\">\n" +
            "AND LEVEL=#{level}\n" +
            "</if>\n" +
            "<if test=\"isMenuLeaf!=null and isMenuLeaf!=''\">\n" +
            "AND ISMENULEAF=#{isMenuLeaf}\n" +
            "</if>\n" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    List<Resource> queryForObject(BaseModel baseModel);

    @Select("<script>SELECT * FROM sys_resource WHERE ID=#{id} AND TYPE=#{type}</script>")
    List<Resource> queryForResourceByIdAndType(String id, String type);
    @Select("<script>SELECT  * FROM sys_resource WHERE 1=1\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"resourceName!=null and resourceName!=''\">\n" +
            "AND RESOURCENAME=#{resourceName}\n" +
            "</if>\n" +
            "<if test=\"type!=null and type!=''\">\n" +
            "AND TYPE=#{type}\n" +
            "</if>\n" +
            "<if test=\"parentId!=null and parentId!=''\">\n" +
            "AND PARENTID=#{parentId}\n" +
            "</if>\n" +
            "<if test=\"parentName!=null and parentName!=''\">\n" +
            "AND PARENTNAME=#{parentName}\n" +
            "</if>\n" +
            "<if test=\"appName!=null and appName!=''\">\n" +
            "AND APPNAME=#{appName}\n" +
            "</if>\n" +
            "<if test=\"url!=null and url!=''\">\n" +
            "AND URL=#{url}\n" +
            "</if>\n" +
            "<if test=\"level!=null and level!=''\">\n" +
            "AND LEVEL=#{level}\n" +
            "</if>\n" +
            "<if test=\"isMenuLeaf!=null and isMenuLeaf!=''\">\n" +
            "AND ISMENULEAF=#{isMenuLeaf}\n" +
            "</if>\n" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "AND IFUSE=#{ifuse}\n" +
            "</if></script>")
    Resource query1ForObject(BaseModel baseModel);
}
