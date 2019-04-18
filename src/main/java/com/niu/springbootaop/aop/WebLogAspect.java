package com.niu.springbootaop.aop;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
   * 正则切点
   */
  @Pointcut("execution(public * com.niu.springbootaop.controller..*.*(..))")
  public void webLog() {

  }

  /**
   * 前置通知
   */
  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    Signature signature = joinPoint.getSignature();

    // 记录下请求内容
    logger.info("URL : " + request.getRequestURL().toString());
    logger.info("HTTP_METHOD : " + request.getMethod());
    logger.info("IP : " + request.getRemoteAddr());

    /**
     * 类名称
     */
    String typeName = signature.getDeclaringTypeName();
    logger.info("CLASS : " + typeName);

    /**
     * 方法名称
     */
    String methodName = signature.getName();
    logger.info("METHOD : " + methodName);

    /**
     * 入参值
     */
    String parameterValue = Arrays.toString(joinPoint.getArgs());
    logger.info("ARGS : " + parameterValue);
  }


  /**
   * 后置通知
   */
  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    logger.info("RESPONSE : " + ret);
  }

}
