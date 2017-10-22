package com.example.filter;

import com.example.config.JwTautil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author zhaoxinguo on 2017/9/13.
 */

@Component
public class JwTAuthenticationFilter extends BasicAuthenticationFilter {

   @Autowired
   private JwTautil jwTautil;

   @Autowired
   private UserDetailsService userDetailsService;

    public JwTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(jwTautil.getJwtHeader());

        if (header == null || !header.startsWith(jwTautil.getTokenHead())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);


        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwTautil.getJwtHeader());
        if (token != null) {
            // parse the token.
            //String parseToken = token.replace(tokenHead, "");
            try {

                String user = Jwts.parser()
                        .setSigningKey(jwTautil.getSecret())
                        .parseClaimsJws(token.replace(jwTautil.getTokenHead(), ""))
                        .getBody()
                        .getSubject();

                UserDetails userDetails = userDetailsService.loadUserByUsername(user);
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            }catch (Exception e){
                e.printStackTrace();
                throw  e;
                //return null;
            }

            return null;
        }
        return null;
    }

}
