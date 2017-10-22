package com.example.filter;

import com.example.config.JwTautil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tangjinhui on 2017/10/20.
 */

@Component
public class TestFilter  extends OncePerRequestFilter {

    @Autowired
    JwTautil jwTautil;


    @Value("${jwt.token.head}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {


        System.out.println("authowired:"+jwTautil.getTokenHead());
        System.out.println("@value:"+tokenHead);

        chain.doFilter(request, response);
    }

}
