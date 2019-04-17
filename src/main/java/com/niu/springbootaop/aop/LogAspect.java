package com.niu.springbootaop.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 20:55
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {

  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  /**
   * pointCut
   */
  @Pointcut("@annotation(com.niu.springbootaop.aop.Action)")
  public void log() {
  }

  /**
   * 前置通知
   */
  @Before("log()")
  public void doBeforeController(JoinPoint joinPoint) {

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Action action = method.getAnnotation(Action.class);
    logger.info("action名称 " + action.value()); // ⑤
  }

  /**
   * 后置通知
   */
  @AfterReturning(pointcut = "log()", returning = "retValue")
  public void doAfterController(JoinPoint joinPoint, Object retValue) {

    logger.info("retValue is:" + retValue);
  }
}
