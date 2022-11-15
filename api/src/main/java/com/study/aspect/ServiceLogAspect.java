package com.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-07-19 22:44
 */
@Aspect
@Component
public class ServiceLogAspect {
    private static final long WARN_LIMIT = 2000;
    private static final long ERROR_LIMIT = 3000;
    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.study.service.impl..*.*(..))")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("====== 开始 {} {} ======",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long sendTime = end - begin;
        if (sendTime > ERROR_LIMIT) {
            log.error("====== 结束 {} {} {}======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    sendTime);
        } else if (sendTime > WARN_LIMIT) {
            log.warn("====== 结束 {} {} {}======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    sendTime
            );
        } else {
            log.info("====== 结束 {} {} {}======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    sendTime);
        }
        return proceed;
    }
}
