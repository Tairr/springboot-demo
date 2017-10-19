package com.example.controller.userinfo;


import com.example.domain.account.Account;
import com.example.domain.account_role.AccountRole;
import com.example.domain.account_user.AccountUser;
import com.example.domain.org.Org;
import com.example.domain.userinfo.UserInfo;
import com.example.service.account.IAccountService;
import com.example.service.account_role.IAccountRoleService;
import com.example.service.account_user.IAccountUserService;
import com.example.service.org.IOrgService;
import com.example.service.userinfo.IUserInfoService;
import com.example.util.*;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * Created by dell on 2017/4/5.
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
@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户相关api")
public class UserController {
    String filePath = "file/";

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountUserService accountUserService;
    @Autowired
    private IAccountRoleService iAccountRoleService;
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String queryForUserList() {
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList = this.userInfoService.queryForObject(new UserInfo());
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("code", "0000");
        resultMap.put("data", userInfoList);
        //resultMap.put("total_count", userInfoList.size());
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户", notes = "查询用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String queryForUser(@PathVariable String id) {
        UserInfo queryUserInfo = new UserInfo();
        queryUserInfo.setId(id);
        UserInfo userInfo = this.userInfoService.queryForObject(queryUserInfo).get(0);
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("code", "0000");
        resultMap.put("data", userInfo);
        //resultMap.put("total_count", userInfoList.size());
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "新增用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String add(@RequestBody UserInfo user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //判断必须不为空字段

        String argsEmptyJudge = StringJudgeEmptyUtils.StringJudgeEmpty(user, new String[]{"phone", "policeNum", "identityCode"});
        if (!StringUtils.isEmpty(argsEmptyJudge)) {
            return argsEmptyJudge;
        }
        //设置id，createTime，updateTime，ifuse
        user = (UserInfo) BaseModelFieldSetUtils.FieldSet(user);
        Map<String, Object> resultMap = new HashMap<>();
        int flag = 0;
        try {
            flag = this.userInfoService.insertUser(user);

        } catch (DuplicateKeyException e) {
            resultMap.put("code", "0001");
            resultMap.put("data", "phone or policeNum repeat");
            Gson gson = new Gson();
            return gson.toJson(resultMap);
        }

        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0001");
            resultMap.put("data", "user insert failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);

    }

    @RequestMapping(value = "delete_select", method = RequestMethod.POST)
    public void delete_change(@RequestBody List<UserInfo> kind) {
        for (int i = 0; i < kind.size(); i++) {
            this.userInfoService.deleteUser(kind.get(i));
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    })
    public String delete(@PathVariable("id") String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        int flag = this.userInfoService.deleteUser(userInfo);
        AccountUser accountUser = new AccountUser();
        accountUser.setUserId(userInfo.getId());
        List<AccountUser> accountUsers = this.accountUserService.queryForObject(accountUser);
        for (AccountUser a : accountUsers) {
            Account account = new Account();
            account.setId(a.getAccountId());
            this.accountService.deleteForObject(account);
            AccountRole accountRole = new AccountRole();
            accountRole.setAccountId(a.getAccountId());
            this.iAccountRoleService.deleteForObject(a.getAccountId());
        }
        this.accountUserService.delete(accountUser);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0011");
            resultMap.put("data", "user delete failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户状态", notes = "更新用户状态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    })
    public String updateUserStatus(@PathVariable("id") String id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户", notes = "更新用户", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String updateUser(@RequestBody UserInfo userInfo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //判断必须不为空字段,不必在判断
//        String argsEmptyJudge = StringJudgeEmptyUtils.StringJudgeEmpty(userInfo, new String[]{"id","phone","policeNum","identityCode"});
//        if(!StringUtils.isEmpty(argsEmptyJudge)){
//            return argsEmptyJudge;
//        }
        UserInfo baseUserInfo = new UserInfo();
        baseUserInfo.setId(userInfo.getId());
        UserInfo oldUserInfo = userInfoService.queryForObject(baseUserInfo).get(0);
        //更新实体的createTime,updateTime,ifuse
        BaseModelFieldSetUtils.FieldSetByOldField(userInfo, oldUserInfo);
        int flag = this.userInfoService.updateUser(userInfo);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0011");
            resultMap.put("data", "user update failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    /*
   上传数据删除
   DELETE FROM sys_userinfo WHERE org LIKE '%北京%'
   DELETE FROM sys_account WHERE username LIKE '%user%'
   DELETE FROM sys_account_user WHERE id != 'au1' AND accountid!='a1'
    */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "上传用户excel", notes = "上传用户excel", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "用户APP", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "用户Excel", required = true, dataType = "File", paramType = "form")
    })
    @ResponseBody
    public String ngUpload(HttpServletRequest request, HttpServletResponse res) throws Exception {
        List<Map<String, String>> fileContentList = new ArrayList<>();
        Gson gson = new Gson();
        System.out.println("in");
        String userExcel = "";
//        //接收参数
//        int id= Integer.parseInt(request.getParameter("id"));
//        System.out.println("id=="+id);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(request.getParameter("appId"))) {
            resultMap.put("code", "10004");
            resultMap.put("data", "appId is null");
            return gson.toJson(resultMap);
        }
        String appId = request.getParameter("appId");
        //解析器解析request的上下文
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        //先判断request中是否包涵multipart类型的数据，
        if (multipartResolver.isMultipart(request)) {
            //再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //这里的name为fileItem的alias属性值，相当于form表单中name
                String name = (String) iter.next();
                System.out.println("name:" + name);
                //根据name值拿取文件
                MultipartFile file = multiRequest.getFile(name);
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    String path = filePath + fileName;
                    userExcel = path;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()) {
                        //如果目标文件所在的目录不存在，则创建父目录
                        localFile.getParentFile().mkdirs();
                        System.out.println("parent:" + localFile.getParentFile().getPath());
                    }
                    //写文件到本地
                    try {
                        //file.transferTo(localFile);
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        resultMap.put("code", "10001");
                        resultMap.put("data", "upload file error");
                        return gson.toJson(resultMap);
                    }
                }
            }
        }
        File file = new File(userExcel);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            try {
                fileContentList = ExcelUtils.readExcel(file.getAbsolutePath());
                System.out.println("这是查看是否能读取EXCEL文档的测试" + fileContentList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                resultMap.put("code", "10002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            } catch (FileFormatException e) {
                e.printStackTrace();
                resultMap.put("code", "10002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            }

            if (fileContentList.size() == 0) {
                resultMap.put("code", "10004");
                resultMap.put("data", "excel is null");
                return gson.toJson(resultMap);
            }
            //查询所有的org，map<orgId,orgName>
            ArrayList<Map<String, String>> orgIdAndOrgNameListMap = (ArrayList<Map<String, String>>) this.orgService.queryOrgIdOrgName();
            //总共的计数
            int totalCount = fileContentList.size();
            //环信注册失败
            int easeFaild = 0;
            //数据库插入成功
            int successCount = 0;
            //数据库插入失败
            int faildCount = 0;
            for (int i = 0; i < fileContentList.size(); i++) {
                Map<String, String> userMap = new HashMap<>();
                userMap = fileContentList.get(i);
                if (userMap.size() == 21) {
                    //获取org name
                    String orgName = userMap.get("13");
                    //获取最后一级orgname
                    String lastOrgName = orgName.substring((orgName.lastIndexOf("-") + 1), orgName.length());
                    String orgId = "";
                    for (Map<String, String> map : orgIdAndOrgNameListMap) {
                        if (map.get("orgname").equals(lastOrgName)) {
                            orgId = map.get("id");
                        }
                    }
                    //此时这个用户存在org
                    if (orgId != "") {
                        UserInfo userInfo = new UserInfo();
                        Account account = new Account();
                        userInfo.setId(UUID.randomUUID().toString());
                        userInfo.setCreateTime(TimeUtils.getCurrentTime());
                        userInfo.setUpdateTime(TimeUtils.getCurrentTime());
                        userInfo.setIfuse("y");
                        account.setId(UUID.randomUUID().toString());

                        //0：手机号
                        userInfo.setPhone(userMap.get("0"));
                        //1：用户id
                        account.setUsername(userMap.get("1"));
                        //2：密码
                        account.setPassword(userMap.get("2"));
                        //3：警号
                        userInfo.setPoliceNum(userMap.get("3"));
                        //4：即时通信ID
                        account.setCommunicationId(userMap.get("4"));
                        //5：姓名
                        userInfo.setRealName(userMap.get("5"));
                        //6：性别代码
                        userInfo.setGender(userMap.get("6"));
                        //7：民族代码
                        userInfo.setNation(userMap.get("7"));
                        //8：籍贯代码
                        userInfo.setNativePlace(userMap.get("8"));
                        //9：出生日期
                        userInfo.setBirthDate(userMap.get("9"));
                        //10：政治面貌
                        userInfo.setPoliticalStatus(userMap.get("10"));
                        //11：婚姻状况
                        userInfo.setMaritalStatus(userMap.get("11"));
                        //12：身份证号
                        userInfo.setIdentityCode(userMap.get("12"));
                        //13：部门
                        userInfo.setOrg(userMap.get("13"));
                        userInfo.setOrgId(orgId);
                        //14：职位
                        userInfo.setStation(userMap.get("14"));
                        //15：家庭地址
                        userInfo.setHomeAddress(userMap.get("15"));
                        //16:办公地址
                        userInfo.setOfficeAddress(userMap.get("16"));
                        //17：办公电话
                        userInfo.setOfficeTelephone(userMap.get("17"));
                        //18：其他联系电话
                        userInfo.setOtherTelephone(userMap.get("18"));
                        //19：邮箱
                        userInfo.setMail(userMap.get("19"));
                        //20:appId
                        account.setAppId(appId);
                        //首先去环信注册用户
                        String easeUserJson = "{\"username\":\"" + account.getUsername() + "\",\"password\":\"" + account.getPassword() + "\",\"nickname\":\"" + userInfo.getRealName() + "\"}";
                        int easeFlag = EasemobUtils.registerUser(easeUserJson);
                        if (easeFlag == 1) {
                            int flag1 = this.userInfoService.insertUser(userInfo);
                            int flag2 = this.accountService.insertForObject(account);
                            AccountUser accountUser = new AccountUser();
                            accountUser.setId(UUID.randomUUID().toString());
                            accountUser.setAccountId(account.getId());
                            accountUser.setUserId(userInfo.getId());
                            if (flag1 == 1 && flag2 == 1) {
                                //全部成功
                                this.accountUserService.insertForObject(accountUser);
                                successCount = successCount + 1;
                            }
                            if (flag1 == 1 && flag2 == 0) {
                                this.userInfoService.deleteUser(userInfo);
                                faildCount = faildCount + 1;
                            }
                            if (flag1 == 0 && flag2 == 1) {
                                this.accountService.deleteForObject(account);
                                faildCount = faildCount + 1;
                            }
                        } else {
                            easeFaild = easeFaild + 1;
                        }
                    } else {
                        faildCount = faildCount + 1;
                    }
                }
            }

            file.delete();
            resultMap.put("code", "10000");
            resultMap.put("data", "excel upload success");
            resultMap.put("easeRegisterFaild", easeFaild);
            resultMap.put("insertSuccess", successCount);
            resultMap.put("insertFaild", faildCount);
            resultMap.put("totalCount", totalCount);
            return gson.toJson(resultMap);
        } else {
            resultMap.put("code", "10003");
            resultMap.put("data", "file not exists");
            return gson.toJson(resultMap);
        }
    }


    @RequestMapping(value = "/org/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取部门下所有用户", notes = "获取部门下所有用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", required = true, dataType = "String", paramType = "path")
    })
    public String getUsersByOrg(@PathVariable("id") String id) {
        List<Org> orgList = new ArrayList<>();
        List<UserInfo> userList = new ArrayList<>();
        //查询当前部门的所有下级部门
        orgList = this.orgService.getAllLeaves(id);
        //查询当前部门
        Org queryOrg = new Org();
        queryOrg.setId(id);
        orgList.add(this.orgService.queryForObject(queryOrg).get(0));
        //遍历所有下级部门（包括本部门），将人员表中orgId等于当前部门ID的人员添加
        for (int i = 0; i < orgList.size(); i++) {
            List<UserInfo> uList = new ArrayList<>();
            UserInfo user = new UserInfo();
            user.setOrgId(orgList.get(i).getId());
            uList = this.userInfoService.queryForObject(user);
            userList.addAll(uList);
        }
        Gson gson = new Gson();
        return gson.toJson(userList);
    }

}
