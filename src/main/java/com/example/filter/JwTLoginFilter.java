package com.example.filter;

import com.example.config.JwTautil;
import com.example.domain.Accountb;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @author zhaoxinguo on 2017/9/12.
 */

@Component
public class JwTLoginFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private JwTautil jwTautil;

    @Autowired
    private AuthenticationManager authenticationManager;


    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Accountb user= new ObjectMapper()
                    .readValue(req.getInputStream(), Accountb.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getName(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        //将权限放入claims 写到jwt的情况
        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("scopes", auth.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        String token = Jwts.builder()
               .setSubject(auth.getName())
               // .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + jwTautil.getExpiration() ))
                .signWith(SignatureAlgorithm.HS512, jwTautil.getSecret()) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
        res.addHeader(jwTautil.getJwtHeader(), jwTautil.getTokenHead() + token);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
