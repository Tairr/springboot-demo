package com.example.service.userinfo.impl;


import com.example.dao.userinfo.IUserInfoDao;
import com.example.domain.app.App;
import com.example.domain.base.BaseModel;
import com.example.domain.userinfo.UserInfo;
import com.example.service.userinfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> queryForObject(BaseModel baseModel) {
        return this.userInfoDao.queryForObject(baseModel);
    }

    @Override
    public int insertUser(BaseModel baseModel) {
        //防止那些不可重复的字段插入之后返回Exception
        int flag = 0;
        try{
            flag = this.userInfoDao.insertUser(baseModel);
        }catch (Exception e){
            flag = 0;
        }
        return flag;
    }

    @Override
    public int deleteUser(BaseModel baseModel) {
        return this.userInfoDao.deleteUser(baseModel);
    }
    @Override
    public int updateUser(BaseModel baseModel) {
        return this.userInfoDao.updateUser(baseModel);
    }

    @Override
    public List<App> queryAppListForObject(BaseModel baseModel) {
        return this.userInfoDao.queryAppListForObject(baseModel);
    }

    @Override
    public List<Map<String, Object>> queryAppListAndCommunicationIdForObject(BaseModel baseModel) {
        return this.userInfoDao.queryAppListAndCommunicationIdForObject(baseModel);
    }
}
