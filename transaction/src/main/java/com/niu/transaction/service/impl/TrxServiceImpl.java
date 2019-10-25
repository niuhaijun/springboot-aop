package com.niu.transaction.service.impl;

import com.niu.common.mapper.TransactionMapper;
import com.niu.common.model.Transaction;
import com.niu.transaction.service.TrxService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: niuhaijun
 * @Date: 2019-10-25 15:02
 * @Version 1.0
 */
@Service
@Slf4j
public class TrxServiceImpl implements TrxService {

  @Autowired
  private TransactionMapper mapper;

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public int save(Transaction transaction) {

    return mapper.insertSelective(transaction);
  }

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public int delete(Integer id) {

    if (id % 3 == 0) {
      throw new RuntimeException("" + id);
    }

    return mapper.deleteByPrimaryKey(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public void mix(Integer id) {

    String content = UUID.randomUUID().toString().replaceAll("-", "");
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");

    try {
      int dn = ((TrxServiceImpl) AopContext.currentProxy()).delete(id);
      log.info("删除的数据个数是 {}", dn);
    }
    catch (Exception e) {
      log.error(e.getMessage());
    }

    Transaction transaction = new Transaction(null, content, uuid);
    int sn = save(transaction);
    log.info("保存的数据个数是 {}", sn);
  }

}
