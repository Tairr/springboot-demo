package com.example.aop;

import com.example.annoation.RequestLimit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
@Order(2)
public class RequestLimitAop {
    private Cache cache;
    EhCacheCacheManager ehCacheCacheManager;
    HttpServletRequest request;

    public String requestLimit(JoinPoint joinPoint) {
        Gson gson = new Gson();
        Map<String, String> resultMap = new HashMap<>();
        this.cache = ehCacheCacheManager.getCache("requestLimitCache");
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }
        if (request == null) {
            resultMap.put("msg", "error");
            resultMap.put("data", "方法中缺失HttpServletRequest参数");
            return gson.toJson(resultMap);
        }
        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);
        if (cache.get(key) == null || ((int) (cache.get(key).get())) == 0) {
            cache.put(key, 1);
        } else {
            int num = (int) cache.get(key).get();
            cache.put(key, num + 1);
        }

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        int limitCount = method.getAnnotation(RequestLimit.class).count();
        long limitTime = method.getAnnotation(RequestLimit.class).limitTime();

        int count = (int) cache.get(key).get();
        if (count > 0) {
            final String limitKey = key;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {    //创建一个新的计时器任务。
                @Override
                public void run() {
                    cache.evict(limitKey);
                }
            };
            timer.schedule(task, limitTime);
            //安排在指定延迟后执行指定的任务。task : 所要安排的任务。10000 : 执行任务前的延迟时间，单位是毫秒。
        }
        if (count > limitCount) {
            //logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
            // throw new RequestLimitException();
            resultMap.put("msg", "error");
            resultMap.put("data", "访问过于频繁");
            return gson.toJson(resultMap);
        }
        resultMap.put("msg", "success");
        resultMap.put("data", "");
        return gson.toJson(resultMap);
    }

    @Around("@annotation(com.example.annoation.RequestLimit)")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String result = requestLimit(proceedingJoinPoint);
        Map<String, String> resultMap = new HashMap<>();
        Map<String, String> paramMap;
        Gson gson = new Gson();
        paramMap = gson.fromJson(result, new TypeToken<Map<String, String>>() {
        }.getType());
        if (paramMap.isEmpty()) {
            resultMap.put("msg", "error");
            resultMap.put("data", "参数错误");
            return gson.toJson(resultMap);
        } else {
            if ("error".equals(paramMap.get("msg"))) {
                return result;
            } else {
                Object[] args = proceedingJoinPoint.getArgs();
                return proceedingJoinPoint.proceed(args);
            }
        }
    }

}
