package com.niu.springbootaop.controller;

import com.niu.springbootaop.controller.param.ExceptionPara;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 19:04
 * @Version 1.0
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {

  @PostMapping("getBadRequestException")
  public void getBadRequestException(ExceptionPara para) {

  }

  @RequestMapping("getBindException")
  public void getBindException(ExceptionPara para) {

  }

  @RequestMapping("getMethodArgumentNotValidException")
  public void getMethodArgumentNotValidException(@Valid ExceptionPara para) {

  }

  @RequestMapping("getRuntimeException")
  public void getRuntimeException(ExceptionPara para) {

    throw new RuntimeException("业务异常!!!");
  }


  @RequestMapping("getException")
  public void getException(ExceptionPara para) throws Exception {

    throw new Exception("未捕获的异常!!!");
  }

}
