package com.chan.ssb.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    @Pointcut("execution(* com.chan.ssb.player.*.*(..))")
    public void playerPackageConfig() {}

    @Pointcut("execution(* com.chan.ssb.user.*.*(..))")
    public void userPackageConfig() {}

    @Pointcut("execution(* com.chan.ssb.team.*.*(..))")
    public void teamPackageConfig() {}

    @Pointcut("bean(*Service*)")
    public void allPackageConfigUsingBean() {}

    @Pointcut("@annotation(com.chan.ssb.aspect.MyLog)")
    public void myLogAnnotation() {}

}