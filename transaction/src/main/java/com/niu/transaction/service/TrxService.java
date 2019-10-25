package com.niu.transaction.service;

import com.niu.common.model.Transaction;

/**
 * @Author: niuhaijun
 * @Date: 2019-10-25 14:54
 * @Version 1.0
 */
public interface TrxService {

  int save(Transaction transaction);

  int delete(Integer id);

  void mix(Integer id);

}
