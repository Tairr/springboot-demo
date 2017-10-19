学习，积累，分享
===============

# SpringSecurity+Jws权限控制
--------------------------

## 参考：
-----

码云项目：https://gitee.com/micai/springboot-springsecurity-jwt-demo；<br>  
博客：http://blog.csdn.net/sxdtzhaoxinguo/article/details/77965226<br>  
博客:使用JWT和Spring Security保护REST API

## 构建模块：
---------


JWTLoginFilter（extends UsernamePasswordAuthenticationFilter ）<br>  
JWTAuthenticationFilter（extends BasicAuthenticationFilter ）  <OncePerRequestFilter ?><br>  
CustomAuthenticationProvider（implements AuthenticationProvider）：自定义身份认证验证组件<br>  
UserDetailsServiceImpl（implements UserDetailsService ）<br>  

## 登录流程：
---------

JWTLoginFilter-attemptAuthentication
                 ↓
CustomAuthenticationProvider-authenticate
                 ↓
UserDetailsServiceImpl-loadUserByUsername《CustomAuthenticationProvider的方法authenticate调用》
                 ↓
JWTLoginFilter-successfulAuthentication



## 拦截需验证请求：
--------------

JWTAuthenticationFilter-doFilterInternal


## 主要参考
--------
码云 springboot-springsecurity-jwt-demo 项目
在此基础，将用户权限放到jwt里，用注解控制方法的访问，做了部分修改



# 其他学习随意测试demo
--------------------

https://github.com/Tairr/demo/tree/master/src/test/java/com/example



