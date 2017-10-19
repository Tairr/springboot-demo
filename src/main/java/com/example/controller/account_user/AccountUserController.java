package com.example.controller.account_user;


import com.example.domain.account.Account;
import com.example.domain.account_role.AccountRole;
import com.example.domain.role.Role;
import com.example.domain.userinfo.UserInfo;
import com.example.service.account.IAccountService;
import com.example.service.account_user.IAccountUserService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/8.
 */
@Controller
@RestController
    @RequestMapping("/accountUser")
public class AccountUserController {
    @Autowired
    private IAccountUserService accountUserService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/{id}/accounts",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户账户列表", notes = "查询用户账户列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String queryUserAccountList(@PathVariable String id) {
        Map<String, Object> resultMap = new HashMap<>();

        List<Account> accountList = this.accountUserService.queryUserAccounts(id);
        resultMap.put("code", "0000");
        resultMap.put("data", accountList);
        Gson gson = new Gson();

        return gson.toJson(resultMap);
    }
    @RequestMapping(value = "/{id}/user",method = RequestMethod.GET)
    @ApiOperation(value = "查询账户的用户信息", notes = "查询账户的用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String queryAccountUser(@PathVariable String id) {
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        List<UserInfo> userInfoList = this.accountUserService.queryAccountUsers(id);
        List<AccountRole> accountRoleList=this.accountService.queryForAccountRole(id);
        List<Role> roleList=this.accountService.queryForRole(accountRoleList.get(0).getRoleId());

        if (userInfoList == null){
            resultMap.put("code", "0001");
            resultMap.put("data", "no user");
            return gson.toJson(resultMap);
        }else{
           UserInfo userInfo = userInfoList.get(0);
            resultMap.put("code", "0000");
            resultMap.put("data", userInfo);
            resultMap.put("role",roleList);
            return gson.toJson(resultMap);
        }
    }
}
