package com.niu.springbootaop.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
public class CoreLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(CoreLogAspect.class);

  /**
   * 注解切点
   */
  @Pointcut("@annotation(com.niu.springbootaop.aop.Action)")
  public void log() {

  }

  /**
   * @Around 方法执行前（后），输出方法的入参（出参）
   */
  @Around("log()")
  public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Action action = method.getAnnotation(Action.class);
    logger.info("action名称 " + action.value());

    Object result = joinPoint.proceed();

    logger.info("retValue is:" + result);

    return result;
  }
}
