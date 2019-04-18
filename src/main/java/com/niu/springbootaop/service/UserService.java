package com.niu.springbootaop.service;

import com.niu.springbootaop.controller.vo.UserVO;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-18 10:30
 * @Version 1.0
 */
public interface UserService {


  UserVO getUserById(Integer uuid);

}
