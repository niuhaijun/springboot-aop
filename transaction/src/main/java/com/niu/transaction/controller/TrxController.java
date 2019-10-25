package com.niu.transaction.controller;

import com.niu.transaction.service.TrxService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: niuhaijun
 * @Date: 2019-10-25 15:05
 * @Version 1.0
 */
@RestController("trx")
public class TrxController {

  @Autowired
  private TrxService service;

  @RequestMapping("transaction")
  public Map<String, Object> transaction(Integer id) {

    service.mix(id);

    Map<String, Object> map = new HashMap<String, Object>() {
      {
        put("code", 200);
        put("message", "success");
        put("data", "测试事务的传播行为");
      }
    };

    return map;
  }

}
