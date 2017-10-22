package com.example.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义身份认证验证组件
 *
 * @author zhaoxinguo on 2017/9/12.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;


//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if(null != userDetails){
           // String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            System.out.println(userDetails.getPassword());
            if(bCryptPasswordEncoder.matches(password,userDetails.getPassword())){

                // 生成令牌
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
                return auth;
            }else {
                throw new BadCredentialsException("密码错误~");
            }
        }else {
            throw new UsernameNotFoundException("用户不存在~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
