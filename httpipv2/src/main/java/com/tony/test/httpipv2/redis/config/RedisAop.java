package com.tony.test.httpipv2.redis.config;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 动态代理
 */
@Aspect
@Component
public class RedisAop {

    private static Logger logger = LoggerFactory.getLogger(RedisAop.class);

    @Pointcut("execution(* com.tony.test.httpipv2.redis.client.RedisAPI.*(..))")
    public void commonPointcut() {
    }

    @Around("commonPointcut()")
    public Object aroundCtrl(ProceedingJoinPoint pjp) throws Throwable {
        StringBuffer METHOD_PARAMS = new StringBuffer("{");
        Gson gson = new Gson();
        // 获取 Method
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取 Method 参数名和值
        String METHOD_NAME = signature.toShortString();
        String[] METHOD_PARAM_NAMES = signature.getParameterNames();
        Object[] METHOD_PARAM_VALUES = pjp.getArgs();
        for (int i = 0; i < METHOD_PARAM_VALUES.length; i++) {
            String name = METHOD_PARAM_NAMES[i];
            Object arg = METHOD_PARAM_VALUES[i];
            if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                continue;
            }
            //参数脱敏处理
//            String jsonArg = DesensitizeUtil.desensitize(arg, name);
            String jsonArg = gson.toJson(arg);
            METHOD_PARAMS.append("\"").append(name).append("\" : ")
                    .append(jsonArg).append(", ");
        }
        if (METHOD_PARAMS.length() > 1) {
            METHOD_PARAMS.deleteCharAt(METHOD_PARAMS.length() - 2);
            METHOD_PARAMS.append("}");
        } else { // 方法没有参数
            METHOD_PARAMS.deleteCharAt(0);
        }

        Object METHOD_RESULT;
        logger.debug("Before Execute start");
        long start = System.currentTimeMillis();
        try {
            METHOD_RESULT = pjp.proceed();
            long timeCost = System.currentTimeMillis() - start;
            logger.debug("Execute start : {} - {} --- " +
                            "Execute end, time cost : {}",
                    pjp.getSignature().toShortString(), METHOD_PARAMS, timeCost);
        } catch (Throwable e) {
            logger.debug("Execute start : {} - {} --- Execute error",
                    pjp.getSignature().toShortString(), METHOD_PARAMS);
            logger.error("AOP ERROR : " + e.getClass().getName() + ": " + e.getMessage(), e);
            throw e;
        }
        return METHOD_RESULT;
    }
}
