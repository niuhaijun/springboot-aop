package com.niu.springbootaop.controller.common;

import static com.niu.springbootaop.controller.common.KeyConstant.KEY_CONTENT_TYPE;
import static com.niu.springbootaop.controller.common.KeyConstant.KEY_RESULT_MESSAGE;
import static com.niu.springbootaop.controller.common.KeyConstant.KEY_RESULT_STATUS;
import static com.niu.springbootaop.controller.common.KeyConstant.KEY_UTF8;
import static com.niu.springbootaop.controller.common.ReturnStatus.API_PARAM_INVALID;
import static com.niu.springbootaop.controller.common.ReturnStatus.SC_BAD_REQUEST;
import static com.niu.springbootaop.controller.common.ReturnStatus.SC_INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 *
 * @Author: niuhaijun
 * @Date: 2019-04-17 18:05
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 请求方式不合适，会引发ConstraintViolationException异常
   * 如使用post请求get接口
   *
   * 如果 @ExceptionHandler 注解中未声明要处理的异常类型，则默认为方法参数列表中的异常类型。
   */
  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public void handleBadRequest(HttpServletResponse response, ConstraintViolationException ex) {

    String message = ex.getConstraintViolations().stream()
        .map(Object::toString).collect(Collectors.joining(","));

    response.setCharacterEncoding(KEY_UTF8);
    response.setContentType(KEY_CONTENT_TYPE);
    response.setStatus(SC_BAD_REQUEST.getValue());

    try (PrintWriter writer = response.getWriter()) {
      JSONObject json = new JSONObject();
      json.put(KEY_RESULT_STATUS, SC_BAD_REQUEST.getValue());
      json.put(KEY_RESULT_MESSAGE, message);
      writer.write(json.toJSONString());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 处理参数绑定异常
   *
   * 例如将字符串"qwe"绑定到Integer类型的字符安上,就会引发BindException
   */
  @ExceptionHandler(BindException.class)
  public void processValidationError(HttpServletResponse response, BindException ex) {

    logger.error(ex.getMessage(), ex);
    response.setCharacterEncoding(KEY_UTF8);
    response.setContentType(KEY_CONTENT_TYPE);
    response.setStatus(SC_BAD_REQUEST.getValue());
    try (PrintWriter writer = response.getWriter()) {
      BindingResult result = ex.getBindingResult();
      List<FieldError> fieldErrors = result.getFieldErrors();
      String errors = this.processFieldErrorsStr(fieldErrors);
      JSONObject json = new JSONObject();
      json.put(KEY_RESULT_STATUS, API_PARAM_INVALID.getValue());
      json.put(KEY_RESULT_MESSAGE, errors);
      writer.write(json.toJSONString());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 处理参数验证异常
   *
   * 例如参数的值不符合Valid框架的约束
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void processValidationError(HttpServletResponse response,
      MethodArgumentNotValidException ex) {

    logger.error(ex.getMessage(), ex);
    response.setCharacterEncoding(KEY_UTF8);
    response.setContentType(KEY_CONTENT_TYPE);
    response.setStatus(SC_BAD_REQUEST.getValue());
    try (PrintWriter writer = response.getWriter()) {
      BindingResult result = ex.getBindingResult();
      List<FieldError> fieldErrors = result.getFieldErrors();
      String errors = this.processFieldErrorsStr(fieldErrors);
      JSONObject json = new JSONObject();
      json.put(KEY_RESULT_STATUS, API_PARAM_INVALID.getValue());
      json.put(KEY_RESULT_MESSAGE, errors);
      writer.write(json.toJSONString());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 处理抛出的业务异常
   *
   * 处理RuntimeException异常
   */
  @ExceptionHandler(RuntimeException.class)
  public void processIspException(HttpServletResponse response, RuntimeException ex) {

    logger.error(ex.getMessage(), ex);
    response.setCharacterEncoding(KEY_UTF8);
    response.setContentType(KEY_CONTENT_TYPE);
    response.setStatus(SC_BAD_REQUEST.getValue());
    try (PrintWriter writer = response.getWriter()) {
      JSONObject json = new JSONObject();
      json.put(KEY_RESULT_STATUS, SC_INTERNAL_SERVER_ERROR.getValue());
      json.put(KEY_RESULT_MESSAGE, ex.getMessage());
      writer.write(json.toJSONString());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 处理未捕获的异常
   *
   * 如果异常从未被其他处理，就由本处理器处理
   */
  @ExceptionHandler(Exception.class)
  public void processException(HttpServletResponse response, Exception ex) {

    logger.error(ex.getMessage(), ex);
    response.setCharacterEncoding(KEY_UTF8);
    response.setContentType(KEY_CONTENT_TYPE);
    response.setStatus(SC_BAD_REQUEST.getValue());
    try (PrintWriter writer = response.getWriter()) {
      JSONObject json = new JSONObject();
      json.put(KEY_RESULT_STATUS, SC_INTERNAL_SERVER_ERROR.getValue());
      json.put(KEY_RESULT_MESSAGE, ex.getMessage());
      writer.write(json.toJSONString());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /**
   * 封装验证失败信息
   */
  private String processFieldErrorsStr(List<FieldError> fieldErrors) {

    return fieldErrors.stream().map(t -> t.getField() + "" + t.getDefaultMessage())
        .collect(Collectors.joining(", "));
  }

}
