package com.niu.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class TransactionApplication {

  public static void main(String[] args) {

    SpringApplication.run(TransactionApplication.class, args);
  }

}
