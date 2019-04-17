package com.niu.springbootaop.controller.param;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 19:07
 * @Version 1.0
 */
@Data
public class ExceptionPara {

  /**
   * 1、ConstraintViolationException
   * 2、BindException
   * 3、MethodArgumentNotValidException
   * 4、RuntimeException
   * 5、Exception
   */
  @NotNull(message = "type必传")
  private Integer type;
}
