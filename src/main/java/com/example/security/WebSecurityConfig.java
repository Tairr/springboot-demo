package com.example.security;


import com.example.filter.JWTAuthenticationFilter;
import com.example.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * @author zhaoxinguo on 2017/9/13.
 */
//@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.header}")
    private String jwtHeader;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.token.head}")
    private String tokenHead;
    @Value("${jwt.secret}")
    private String secret;

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/ac/signup").permitAll() // 所有 /ac/signup 的POST请求 都放行
                .anyRequest().authenticated()  // 所有请求需要身份认证
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager(),jwtHeader,expiration,tokenHead,secret))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),userDetailsService,jwtHeader,expiration,tokenHead,secret));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder));
    }

}
