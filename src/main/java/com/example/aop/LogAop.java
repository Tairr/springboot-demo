package com.example.aop;

import com.example.annoation.Log;
import com.example.util.TimeUtils;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/1/17.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@Component
@Aspect
@Order(3)
public class LogAop {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.example.annoation.Log)")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Principal principal = null;
        HttpServletRequest request = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Principal) {
                principal = (Principal) args[i];
                break;
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }
        if (request == null) {
            return proceedingJoinPoint.proceed(args);
        }

            MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = ms.getMethod();
            String operation = method.getAnnotation(Log.class).name();
            Object o = proceedingJoinPoint.proceed(args);
            Map<String, Object> logMap = new HashMap<>();
            logMap.put("operName", operation);
            if (request.getUserPrincipal()!=null){
                logMap.put("username", request.getUserPrincipal().getName());
            }
            logMap.put("ip", getCilentIp(request));
            logMap.put("method", method.getName());
            logMap.put("result", o);
            logMap.put("time", TimeUtils.getCurrentTime());
            for (int i = 0; i < args.length; i++) {
                logMap.put("param_" + i + ":", args[i].toString());
            }

            Gson gson = new Gson();
            logger.info(gson.toJson(logMap));
            return o;


    }

    public String getCilentIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
