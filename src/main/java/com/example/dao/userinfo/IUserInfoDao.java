package com.example.dao.userinfo;


import com.example.domain.app.App;
import com.example.domain.base.BaseModel;
import com.example.domain.userinfo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
@Repository("userInfoDao")
public interface IUserInfoDao {
    @Select("<script>SELECT  * FROM sys_userinfo WHERE ifuse!='n'\n" +
            "<if test=\"id!=null and id!=''\">\n" +
            "AND ID=#{id}\n" +
            "</if>\n" +
            "<if test=\"realName!=null and realName!=''\">\n" +
            "AND REALNAME=#{realName}\n" +
            "</if>\n" +
            "<if test=\"phone!=null and phone!=''\">\n" +
            "AND PHONE=#{phone}\n" +
            "</if>\n" +
            "<if test=\"mail!=null and mail!=''\">\n" +
            "AND MAIL=#{mail}\n" +
            "</if>\n" +
            "<if test=\"org!=null and org!=''\">\n" +
            "AND ORG=#{org}\n" +
            "</if>\n" +
            "<if test=\"orgId!=null and orgId!=''\">\n" +
            "AND ORGID=#{orgId}\n" +
            "</if>\n" +
            "<if test=\"station!=null and station!=''\">\n" +
            "AND STATION=#{station}\n" +
            "</if>\n" +
            "</script>")
    List<UserInfo> queryForObject(BaseModel baseModel);
    @Insert("<script> INSERT INTO sys_userinfo" +
            " (id,ifUse,createTime,updateTime,orderNum,realName,phone,mail,org,station,gender,nation,nativePlace,birthDate,politicalStatus,maritalStatus,nickName,picture,policeNum,identityCode,qr,qq,weChat,jobCode,autoGraph,homeAddress,officeAddress,otherAddress,officeTelephone,otherTelephone,ifHideNum,orgId) VALUES\n" +
            "  (#{id},#{ifuse},#{createTime},#{updateTime},#{orderNum},#{realName},#{phone},#{mail},#{org},#{station},#{gender},#{nation},#{nativePlace},#{birthDate},#{politicalStatus},#{maritalStatus},#{nickName},#{picture},#{policeNum},#{identityCode},#{qr},#{qq},#{weChat},#{jobCode},#{autoGraph},#{homeAddress},#{officeAddress},#{otherAddress},#{officeTelephone},#{otherTelephone},#{ifHideNum},#{orgId})" +
            "</script>")
    int insertUser(BaseModel baseModel);
    //更新用户
    @Update("<script> UPDATE sys_userinfo " +
            "<trim prefix=\"set\" suffixOverrides=\",\">" +
            "<if test=\"ifuse!=null and ifuse!=''\">\n" +
            "ifuse=#{ifuse},\n" +
            "</if>\n" +
            "<if test=\"createTime!=null and createTime!=''\">\n" +
            "createTime=#{createTime},\n" +
            "</if>\n" +
            "<if test=\"updateTime!=null and updateTime!=''\">\n" +
            "updateTime=#{updateTime},\n" +
            "</if>\n" +
            "<if test=\"orderNum!=null and orderNum!=''\">\n" +
            "orderNum=#{orderNum},\n" +
            "</if>\n" +
            "<if test=\"realName!=null and realName!=''\">\n" +
            "realName=#{realName},\n" +
            "</if>\n" +
            "<if test=\"phone!=null and phone!=''\">\n" +
            "phone=#{phone},\n" +
            "</if>\n" +
            "<if test=\"mail!=null and mail!=''\">\n" +
            "mail=#{mail},\n" +
            "</if>\n" +
            "<if test=\"org!=null and org!=''\">\n" +
            "org=#{org},\n" +
            "</if>\n" +
            "<if test=\"station!=null and station!=''\">\n" +
            "station=#{station},\n" +
            "</if>\n" +
            "<if test=\"gender!=null and gender!=''\">\n" +
            "gender=#{gender},\n" +
            "</if>\n" +
            "<if test=\"nation!=null and nation!=''\">\n" +
            "nation=#{nation},\n" +
            "</if>\n" +
            "<if test=\"nativePlace!=null and nativePlace!=''\">\n" +
            "nativePlace=#{nativePlace},\n" +
            "</if>\n" +
            "<if test=\"birthDate!=null and birthDate!=''\">\n" +
            "birthDate=#{birthDate},\n" +
            "</if>\n" +
            "<if test=\"politicalStatus!=null and politicalStatus!=''\">\n" +
            "politicalStatus=#{politicalStatus},\n" +
            "</if>\n" +
            "<if test=\"maritalStatus!=null and maritalStatus!=''\">\n" +
            "maritalStatus=#{maritalStatus},\n" +
            "</if>\n" +
            "<if test=\"nickName!=null and nickName!=''\">\n" +
            "nickName=#{nickName},\n" +
            "</if>\n" +
            "<if test=\"picture!=null and picture!=''\">\n" +
            "picture=#{picture},\n" +
            "</if>\n" +
            "<if test=\"policeNum!=null and policeNum!=''\">\n" +
            "policeNum=#{policeNum},\n" +
            "</if>\n" +
            "<if test=\"identityCode!=null and identityCode!=''\">\n" +
            "identityCode=#{identityCode},\n" +
            "</if>\n" +
            "<if test=\"qr!=null and qr!=''\">\n" +
            "qr=#{qr},\n" +
            "</if>\n" +
            "<if test=\"qq!=null and qq!=''\">\n" +
            "qq=#{qq},\n" +
            "</if>\n" +
            "<if test=\"weChat!=null and weChat!=''\">\n" +
            "weChat=#{weChat},\n" +
            "</if>\n" +
            "<if test=\"jobCode!=null and jobCode!=''\">\n" +
            "jobCode=#{jobCode},\n" +
            "</if>\n" +
            "<if test=\"autoGraph!=null and autoGraph!=''\">\n" +
            "autoGraph=#{autoGraph},\n" +
            "</if>\n" +
            "<if test=\"homeAddress!=null and homeAddress!=''\">\n" +
            "homeAddress=#{homeAddress},\n" +
            "</if>\n" +
            "<if test=\"officeAddress!=null and officeAddress!=''\">\n" +
            "officeAddress=#{officeAddress},\n" +
            "</if>\n" +
            "<if test=\"otherAddress!=null and otherAddress!=''\">\n" +
            "otherAddress=#{otherAddress},\n" +
            "</if>\n" +
            "<if test=\"officeTelephone!=null and officeTelephone!=''\">\n" +
            "officeTelephone=#{officeTelephone},\n" +
            "</if>\n" +
            "<if test=\"otherTelephone!=null and otherTelephone!=''\">\n" +
            "otherTelephone=#{otherTelephone},\n" +
            "</if>\n" +
            "<if test=\"ifHideNum!=null and ifHideNum!=''\">\n" +
            "ifHideNum=#{ifHideNum},\n" +
            "</if>\n" +
            "<if test=\"orgId!=null and orgId!=''\">\n" +
            "orgId=#{orgId},\n" +
            "</if>\n" +
            "</trim>"+
            " WHERE id=#{id}</script>")
    int updateUser(BaseModel baseModel);
    @Delete("<script>DELETE FROM sys_userinfo WHERE ID=#{id}</script>")
    int deleteUser(BaseModel baseModel);
    //查询当前用户的app列表
    @Select("<script>SELECT ta.* FROM sys_userinfo su " +
            "LEFT JOIN sys_account_user sau ON su.`ID`=sau.`USERID` " +
            "LEFT JOIN sys_account sa ON sau.`ACCOUNTID`=sa.`ID` " +
            "RIGHT JOIN tbl_app ta ON sa.`APPID`=ta.`ID` " +
            "WHERE su.`ID`=#{id}</script>")
    List<App> queryAppListForObject(BaseModel baseModel);
    //查询当前用户的app列表+环信ID
    @Select("<script>SELECT sa.`ID` AS appId,sa.`COMMUNICATIONID` AS communicationId,ta.* FROM sys_userinfo su " +
            "LEFT JOIN sys_account_user sau ON su.`ID`=sau.`USERID` " +
            "LEFT JOIN sys_account sa ON sau.`ACCOUNTID`=sa.`ID` " +
            "RIGHT JOIN tbl_app ta ON sa.`APPID`=ta.`ID` " +
            "WHERE su.`ID`=#{id}</script>")
    List<Map<String,Object>> queryAppListAndCommunicationIdForObject(BaseModel baseModel);
}
