package com.niu.advice.service.impl;

import com.niu.advice.aop.Action;
import com.niu.advice.controller.vo.UserVO;
import com.niu.advice.service.UserService;
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

  @Action(value = "根据用户ID查询用户信息")
  @Override
  public UserVO getUserById(Integer id, String name) {

    return map.getOrDefault(id, map.get(0));
  }

  @Action(value = "根据用户ID更新用户信息")
  @Override
  public void updateUserById(Integer id, String name) {

    UserVO userVO = map.get(id);
    if (userVO != null) {
      userVO.setUsername(name);
    }
  }
}
