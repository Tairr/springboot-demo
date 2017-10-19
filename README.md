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


### 10.19更新
------------

考虑权限数据较多造成token过长，将权限校验更改为验证token后数据库查询验证

******************************************

JWT携带权限只需3步：

1.JWTLoginFilter 中 successfulAuthentication 方法将权限信息加入token返回请求方;

2.JWTAuthenticationFilter 中 getAuthentication 方法将权限信息从token解析解析;

3.在需要增加权限校验的方法上方加入@PreAuthorize, @PostAuthorize, @Secured等注解，进入请求方法时会自动校验
（注解使用参考：http://blog.csdn.net/w605283073/article/details/51327182）


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



