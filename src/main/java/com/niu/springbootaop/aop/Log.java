//package com.niu.springbootaop.aop;
//
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Inherited;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * 对标记方法打印入参、出参的日志，标记也可以用在类上，用于表示打印其所有方法的入参出参日志
// *
// * @Author: niuhaijun
// * @Date: 2019-04-18 10:37
// * @Version 1.0
// */
//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//public @interface Log {
//
//  /**
//   * 日志描述
//   */
//  String tag() default "";
//
//  /**
//   * 参数名称,务必用英文逗号分隔
//   */
//  String params() default "";
//}
