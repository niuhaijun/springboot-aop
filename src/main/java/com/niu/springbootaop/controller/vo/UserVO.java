package com.niu.springbootaop.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-18 10:40
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class UserVO {

  private String id;
  private String username;
  private String password;
}
