//package com.niu.springbootaop.aop;
//
//import static com.alibaba.fastjson.JSON.toJSONStringWithDateFormat;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import java.lang.reflect.Method;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: niuhaijun
// * @Date: 2019-04-18 10:35
// * @Version 1.0
// */
//@Aspect
//@Order(100)
//@Component
//public class ReqLogAspect {
//
//  public static final Logger logger = LoggerFactory.getLogger(ReqLogAspect.class);
//  public static final String DATE_FORMAT = "yyyy:MM:dd HH:mm:ss";
//  public static final String STRING_START = " <= ";
//  public static final String STRING_END = " => ";
//
//  /**
//   * 切点：service包中定义的任意public方法
//   */
//  @Pointcut("execution(public * com.xes.stone.complaint.core.service.impl.*.* (..))")
//  private void log() {
//
//  }
//
//  /**
//   * @Around 方法执行前（后），输出方法的入参（出参）
//   */
//  @Around("log()")
//  public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//    // 获取方法名和类名
//    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//    Method method = signature.getMethod();
//    Class<?> targetClass = method.getDeclaringClass();
//
//    // 获取类注解@Log和方法注解@Log
//    Log classAnnotation = targetClass.getAnnotation(Log.class);
//    Log methodAnnotation = method.getAnnotation(Log.class);
//
//    //注解为空，执行目标方法并返回
//    if (null == classAnnotation && null == methodAnnotation) {
//      return joinPoint.proceed();
//    }
//
//    //获取注解属性:tag，params
//    String tag = methodAnnotation != null ? ("{" + methodAnnotation.tag() + "}") :
//        classAnnotation != null ? ("{" + classAnnotation.tag() + "}") :
//            "";
//    String paramsNames = methodAnnotation != null ? methodAnnotation.params() : "";
//
//    //拼凑目标类名和参数名 className例:[com.tal.peiypipad.Controller.OrderService]
//    String[] className = targetClass.getName().split("\\.");
//    String target = className[className.length - 1] + "." + method.getName() + "()";
//
//    //打印输入日志
//    logger.info("<<<<<<<<<< " + tag + target + STRING_START +
//        getInputWithParamsNames(joinPoint.getArgs(), paramsNames, methodAnnotation));
//
//    //执行目标代码
//    long start = System.currentTimeMillis();
//    Object result = joinPoint.proceed();
//    long timeConsuming = System.currentTimeMillis() - start;
//
//    String output = ">>>>>>>>>>" + tag + target + STRING_END;
//    try {
//      output += toJSONStringWithDateFormat(result, DATE_FORMAT,
//          SerializerFeature.WriteMapNullValue);
//    } catch (Exception e) {
//      output += " ";
//    }
//    logger.info(output + "[" + "耗时" + timeConsuming + "ms]" + System.lineSeparator());
//
//    return result;
//
//  }
//
//
//  /**
//   * 拼接带有参数名称的输入信息
//   *
//   * @param args             入参
//   * @param paramsNames      入参名称
//   * @param methodAnnotation 方法注解@Log
//   * @return 带有参数名称的输入信息
//   */
//  private String getInputWithParamsNames(Object[] args, String paramsNames, Log methodAnnotation) {
//    // 拼接入参信息
//    StringBuffer inputArgsInfo = new StringBuffer();
//    try {
//      // 获取参数名个数、参数个数
//      String[] paramsNamesArray = paramsNames.split(",");
//      int paramsNamesNum = paramsNamesArray.length;
//      int ArgsNum = args.length;
//
//      // 方法注解为空||参数个数为零||注解中未写参数名称||参数名称个数和参数值个数不一致
//      if (null == methodAnnotation || ArgsNum == 0 || "".equals(paramsNames)
//          || ArgsNum != paramsNamesNum) {
//        paramsNamesArray = new String[ArgsNum];
//        for (int i = 0; i < ArgsNum; i++) {
//          paramsNamesArray[i] = "param" + (i + 1);
//        }
//      }
//
//      inputArgsInfo.append("{");
//      for (int i = 0; i < ArgsNum; i++) {
//        String arg = JSONObject.toJSONStringWithDateFormat(args[i], DATE_FORMAT,
//            SerializerFeature.WriteMapNullValue);
//        String paramName = paramsNamesArray[i].trim() + ":";
//
//        if (i == ArgsNum - 1) {
//          inputArgsInfo.append(paramName).append(arg);
//          break;
//        }
//        inputArgsInfo.append(paramName).append(arg).append(",");
//      }
//      inputArgsInfo.append("}");
//    } catch (Exception e) {
//      return "";
//    }
//    return inputArgsInfo.toString();
//  }
//}
//
//
//
