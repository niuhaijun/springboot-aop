package com.niu.springbootaop.service.impl;

import com.niu.springbootaop.aop.Action;
import com.niu.springbootaop.controller.vo.UserVO;
import com.niu.springbootaop.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-18 10:30
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  private Map<String, UserVO> map = new HashMap<String, UserVO>() {
    {
      put("1", new UserVO("1", "牛海军", "123456"));
      put("2", new UserVO("2", "黄文君", "abcdef"));
    }
  };


  @Action
  @Override
  public UserVO getUserById(String uuid) {

    return map.get(uuid);
  }
}
