package com.example.domain.userinfo;


import com.example.domain.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@SuppressWarnings("unused")
@ApiModel(value = "用户对象",description = "UserInfo")
public class UserInfo extends BaseModel {
    @ApiModelProperty(value = "姓名",required = false)
    private String realName;
    @ApiModelProperty(value = "电话",required = true)
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String mail;
    @ApiModelProperty(value = "部门名称")
    private String org;
    @ApiModelProperty(value = "部门id")
    private String orgId;
    @ApiModelProperty(value = "职位")
    private String station;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "民族")
    private String nation;
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;
    @ApiModelProperty(value = "生日")
    private String birthDate;
    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;
    @ApiModelProperty(value = "婚姻状况")
    private String maritalStatus;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像")
    private String picture;
    @ApiModelProperty(value = "警号",required = true)
    private String policeNum;
    @ApiModelProperty(value = "身份证",required = true)
    private String identityCode;
    @ApiModelProperty(value = "二维码")
    private String qr;
    @ApiModelProperty(value = "qq号")
    private String qq;
    @ApiModelProperty(value = "微信")
    private String weChat;
    @ApiModelProperty(value = "职务代码")
    private String jobCode;
    @ApiModelProperty(value = "个性签名")
    private String autoGraph;
    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;
    @ApiModelProperty(value = "办公地址")
    private String officeAddress;
    @ApiModelProperty(value = "其他地址")
    private String otherAddress;
    @ApiModelProperty(value = "办公电话")
    private String officeTelephone;
    @ApiModelProperty(value = "其他电话")
    private String otherTelephone;
    @ApiModelProperty(value = "是否隐藏号码")
    private String ifHideNum;

    public UserInfo() {
    }

    public UserInfo(String realName, String phone, String mail, String org, String orgId, String station, String gender, String nation, String nativePlace, String birthDate, String politicalStatus, String maritalStatus, String nickName, String picture, String policeNum, String identityCode, String qr, String qq, String weChat, String jobCode, String autoGraph, String homeAddress, String officeAddress, String otherAddress, String officeTelephone, String otherTelephone, String ifHideNum) {
        this.realName = realName;
        this.phone = phone;
        this.mail = mail;
        this.org = org;
        this.orgId = orgId;
        this.station = station;
        this.gender = gender;
        this.nation = nation;
        this.nativePlace = nativePlace;
        this.birthDate = birthDate;
        this.politicalStatus = politicalStatus;
        this.maritalStatus = maritalStatus;
        this.nickName = nickName;
        this.picture = picture;
        this.policeNum = policeNum;
        this.identityCode = identityCode;
        this.qr = qr;
        this.qq = qq;
        this.weChat = weChat;
        this.jobCode = jobCode;
        this.autoGraph = autoGraph;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
        this.otherAddress = otherAddress;
        this.officeTelephone = officeTelephone;
        this.otherTelephone = otherTelephone;
        this.ifHideNum = ifHideNum;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public void setOfficeTelephone(String officeTelephone) {
        this.officeTelephone = officeTelephone;
    }

    public void setOtherTelephone(String otherTelephone) {
        this.otherTelephone = otherTelephone;
    }

    public void setIfHideNum(String ifHideNum) {
        this.ifHideNum = ifHideNum;
    }

    public String getOfficeAddress() {

        return officeAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public String getOfficeTelephone() {
        return officeTelephone;
    }

    public String getOtherTelephone() {
        return otherTelephone;
    }

    public String getIfHideNum() {
        return ifHideNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public void setAutoGraph(String autoGraph) {
        this.autoGraph = autoGraph;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getGender() {

        return gender;
    }

    public String getNation() {
        return nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPicture() {
        return picture;
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public String getQr() {
        return qr;
    }

    public String getQq() {
        return qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public String getJobCode() {
        return jobCode;
    }

    public String getAutoGraph() {
        return autoGraph;
    }

    public String getHomeAddress() {
        return homeAddress;
    }
}
