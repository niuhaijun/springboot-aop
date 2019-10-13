package com.niu.advice.service;

import com.niu.advice.controller.vo.UserVO;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-18 10:30
 * @Version 1.0
 */
public interface UserService {


  UserVO getUserById(Integer id, String name);

  void updateUserById(Integer id, String name);

}
