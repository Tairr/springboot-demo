package com.example.service.userinfo;


import com.example.domain.app.App;
import com.example.domain.base.BaseModel;
import com.example.domain.userinfo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/6.
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
public interface IUserInfoService {
    List<UserInfo> queryForObject(BaseModel baseModel);
    int insertUser(BaseModel baseModel);
    int deleteUser(BaseModel baseModel);
    //更新用户信息
    int updateUser(BaseModel baseModel);
    //查询当前用户的app列表
    List<App> queryAppListForObject(BaseModel baseModel);
    //查询当前用户的app列表+环信ID
    List<Map<String,Object>> queryAppListAndCommunicationIdForObject(BaseModel baseModel);
}
