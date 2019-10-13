package com.niu.advice.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 20:53
 * @Version 1.0
 */
@Target({PARAMETER, METHOD})
@Retention(RUNTIME)
@Documented
public @interface Action {

  String value() default "";
}