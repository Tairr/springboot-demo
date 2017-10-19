#参考：
-----

码云项目：https://gitee.com/micai/springboot-springsecurity-jwt-demo
博客：http://blog.csdn.net/sxdtzhaoxinguo/article/details/77965226
博客:使用JWT和Spring Security保护REST API

#构建模块：
---------


JWTLoginFilter（extends UsernamePasswordAuthenticationFilter ）
JWTAuthenticationFilter（extends BasicAuthenticationFilter ）  <OncePerRequestFilter ?>
CustomAuthenticationProvider（implements AuthenticationProvider）：自定义身份认证验证组件
UserDetailsServiceImpl（implements UserDetailsService ）

#登录流程：
---------

JWTLoginFilter-attemptAuthentication
                 ↓
CustomAuthenticationProvider-authenticate
                 ↓
UserDetailsServiceImpl-loadUserByUsername《CustomAuthenticationProvider的方法authenticate调用》
                 ↓
JWTLoginFilter-successfulAuthentication



#拦截需验证请求：
--------------

JWTAuthenticationFilter-doFilterInternal


#主要参考springboot-springsecurity-jwt-demo项目，因为增加了权限到jwt里，做了部分修改
---------------
