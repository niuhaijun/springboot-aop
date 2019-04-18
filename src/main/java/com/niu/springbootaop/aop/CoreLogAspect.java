package com.niu.springbootaop.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
   * 【@Around】 方法执行前（后），输出方法的入参（出参）
   */
  @Around("log()")
  public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    method.getParameters();

    /**
     * 类的名称
     */
    String targetTypeName = signature.getDeclaringTypeName();
    logger.info("类的名称 ——》" + targetTypeName);

    /**
     * 方法的名称
     */
    String methodName = method.getName();
    String methodName1 = signature.getName();
    logger.info("方法的名称 ——》" + methodName + "; " + methodName1);

    /**
     * 方法的返回值类型名称
     */
    String returnTypeName = signature.getReturnType().getName();
    logger.info("方法的返回值类型名称 ——》" + returnTypeName);

    /**
     * 方法的参数类型的名称
     */
    String parameterTypeName = Stream.of(method.getParameterTypes()).map(Class::getName)
        .collect(Collectors.joining(", "));
    String parameterTypeName1 = Stream.of(method.getParameters()).map(Parameter::getType)
        .map(Object::toString).collect(Collectors.joining(", "));
    logger.info("方法的参数类型的名称 ——》" + parameterTypeName + "; " + parameterTypeName1);

    /**
     * 方法的参数名称
     */
    String parameterName = Stream.of(method.getParameters()).map(Parameter::getName).collect(
        Collectors.joining(", "));
    logger.info("方法的参数名称——》" + parameterName);

    /**
     * 方法的参数的值
     */
    String parameterValue = Stream.of(joinPoint.getArgs())
        .map(t -> t == null ? "null" : t.toString()).collect(Collectors.joining(", "));
    logger.info("方法的参数值——》" + parameterValue);

    Action action = method.getAnnotation(Action.class);
    logger.info("action名称 ——》" + action.value());
    Object result = joinPoint.proceed();
    logger.info("retValue is:" + result);

    return result;
  }
}
