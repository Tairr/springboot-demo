package com.example.util;


import com.example.domain.account.Account;
import com.example.service.account.IAccountService;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by Administrator on 2017/5/19.
 */
public class AccountPasswordUtils {
    //验证密码
    public static int validatePassword(String id, String password, IAccountService accountService){
        Account searchAccount = new Account();
        searchAccount.setId(id);
        Account account = accountService.queryForObject(searchAccount).get(0);
        String currPassword = account.getPassword();
        String salt = account.getSalt();
        String passwordMD5 =new Md5PasswordEncoder().encodePassword(password,salt);
        if(currPassword.equals(passwordMD5)){
            return 1;
        }else {
            return 0;
        }
    }
    //修改密码
    public static int updatePassword(String id,String password,IAccountService accountService){
        try {
            Account searchAccount = new Account();
            searchAccount.setId(id);
            Account account = accountService.queryForObject(searchAccount).get(0);
            //获取长度为6的随机字符串
            String randomSalt = MakeFixLenthStringUtils.getFixLenthString(6);
            account.setSalt(randomSalt);
            String usePassWord =new Md5PasswordEncoder().encodePassword(password,randomSalt);
            account.setPassword(usePassWord);
            account.setUpdateTime(TimeUtils.getCurrentTime());
            accountService.updateForObject(account);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }
}
