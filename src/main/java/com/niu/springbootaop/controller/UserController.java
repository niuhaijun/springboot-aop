package com.niu.springbootaop.controller;

import com.niu.springbootaop.controller.vo.UserVO;
import com.niu.springbootaop.service.UserService;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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


  @PostMapping
  public UserVO getUser(@NotEmpty String uuid) {

    return userService.getUserById(uuid);
  }

}
