package com.example.domain.account;


import com.example.domain.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by dell on 2017/1/11.
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
@SuppressWarnings("unused")
@ApiModel(value = "账户对象",description = "Accountb")
public class Account extends BaseModel {

    @ApiModelProperty(value = "账户名称",required = true)
    private String username;
    @ApiModelProperty(value = "账户密码",required = true)
    private String password;
    @ApiModelProperty(value = "账户盐")
    private String salt;
    @ApiModelProperty(value = "账户token")
    private String token;
    @ApiModelProperty(value = "账户appId",required = true)
    private String appId;
    @ApiModelProperty(value = "环信ID",required = true)
    private String communicationId;

    public Account() {
    }

    public Account(String id, String ifuse, String createTime, String updateTime, String orderNum, String username, String password, String salt, String token, String appId, String communicationId) {
        super(id, ifuse, createTime, updateTime, orderNum);
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.token = token;
        this.appId = appId;
        this.communicationId = communicationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }

    public String getCommunicationId() {

        return communicationId;
    }
}
