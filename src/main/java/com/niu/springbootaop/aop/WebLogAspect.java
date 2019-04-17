package com.niu.springbootaop.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 20:33
 * @Version 1.0
 */
@Aspect
@Component
public class WebLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  /**
   * 切点
   */
  @Pointcut("execution(public * com.niu.springbootaop.controller..*.*(..))")
  public void webLog() {

  }


  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    // 记录下请求内容
    logger.info("URL : " + request.getRequestURL().toString());
    logger.info("HTTP_METHOD : " + request.getMethod());
    logger.info("IP : " + request.getRemoteAddr());
    logger.info(
        "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
            .getSignature().getName());
    logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    logger.info("RESPONSE : " + ret);
  }

}
