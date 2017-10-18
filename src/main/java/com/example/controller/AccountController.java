package com.example.controller;

import com.example.domain.Account;
import com.example.service.IAccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tangjinhui on 2017/10/10.
 */

@RestController
@RequestMapping("/ac")
public class AccountController {
    @Autowired
    IAccountService accountService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取账户列表",notes="")
    public  List<Account> getAccounts(){
        return accountService.findAccountList();
    }

    @ApiOperation(value = "根据id获取账户",notes = "")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  Account getAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }

    @ApiOperation(value = "根据name获取账户",notes = "")
    @ApiImplicitParam(name = "name",value = "用户name",required = true,dataType = "int")
    @RequestMapping(value = "/fbname/{name}",method = RequestMethod.GET)
    public  Account getAccountByName(@PathVariable("name") String name){
        return accountService.findAccountByName(name);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public  String postAccount(@RequestBody Map<String, String> map){
        Account account=new Account();
        account.setPassword(DigestUtils.md5DigestAsHex((map.get("password")).getBytes()));
        account.setName(map.get("name"));
        account.setMoney(2.0);
        int t= accountService.add(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }
}
