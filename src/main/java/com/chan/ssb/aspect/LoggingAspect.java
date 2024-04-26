package com.chan.ssb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("com.chan.ssb.aspect.CommonPointcutConfig.playerPackageConfig()")
    public void logMethodCallBefore(JoinPoint joinPoint) {
        logger.info("Before Aspect - {}", joinPoint);
    }

    @After("com.chan.ssb.aspect.CommonPointcutConfig.userPackageConfig()")
    public void logMethodCallAfter(JoinPoint joinPoint) {
        logger.info("After Aspect - {} is called", joinPoint);
    }

    @AfterThrowing(
            pointcut = "com.chan.ssb.aspect.CommonPointcutConfig.teamPackageConfig()",
            throwing = "exception"
    )
    public void logMethodCallAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing Aspect - {} is called {}", joinPoint, exception);
    }

    @AfterReturning(
            pointcut = "com.chan.ssb.aspect.CommonPointcutConfig.allPackageConfigUsingBean()",
            returning = "resultValue"
    )
    public void logMethodCallAfterReturning(JoinPoint joinPoint, Object resultValue) {
        logger.info("AfterReturning Aspect - {} is called {}", joinPoint, resultValue);
    }

    @Around("com.chan.ssb.aspect.CommonPointcutConfig.myLogAnnotation()")
    public Object logMethodCallAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Around Aspect before - {} is called", proceedingJoinPoint);
        Object returnValue = proceedingJoinPoint.proceed();
        logger.info("Around Aspect after - {} is called", proceedingJoinPoint);

        return returnValue;
    }
}
