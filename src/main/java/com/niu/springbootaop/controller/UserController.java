package com.niu.springbootaop.controller;

import com.niu.springbootaop.controller.vo.UserVO;
import com.niu.springbootaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-18 10:39
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping(value = "getUser")
  public UserVO getUser(Integer id) {

    return userService.getUserById(id);
  }

}
