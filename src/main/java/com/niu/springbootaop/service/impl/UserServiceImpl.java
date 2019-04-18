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

  private Map<Integer, UserVO> map = new HashMap<Integer, UserVO>() {
    {
      put(0, new UserVO("0", "其他", "其他"));
      put(1, new UserVO("1", "牛海军", "123456"));
      put(2, new UserVO("2", "黄文君", "abcdef"));
    }
  };


  @Action
  @Override
  public UserVO getUserById(Integer id) {

    return map.getOrDefault(id, map.get(0));
  }

  @Action
  @Override
  public void updateUserById(Integer id) {

    UserVO userVO = map.get(id);
    if (userVO != null) {

    }

  }
}
