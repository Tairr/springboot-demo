package com.example.security;


import com.example.dao.AccountDao;
import com.example.domain.Account;
import com.example.domain.GrantedAuthorityImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Collections.emptyList;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountDao accountDao;

    // 通过构造器注入MyUserRepository
    public UserDetailsServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account myUser = accountDao.findAccountByName(username);
        if(myUser == null){
            throw new UsernameNotFoundException(username);
        }
        //获取权限...
        // 这里设置权限和角色
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
        authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );

        return new org.springframework.security.core.userdetails.User(myUser.getName(), myUser.getPassword(), authorities);
    }

}
