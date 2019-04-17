package com.niu.springbootaop.common;

import java.util.HashSet;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-17 18:08
 * @Version 1.0
 */
public enum ReturnStatus {
  SC_OK(200, "成功"),
  SC_NOT_MODIFIED(304, "未处理"),
  SC_BAD_REQUEST(400, "服务器不理解请求的语法"),
  SC_INTERNAL_SERVER_ERROR(500, "服务器遇到错误"),
  API_NET_EXCEPTION(521, "网络异常"),
  API_INTERFACE_EXCEPTION(548, "接口调用异常"),
  API_STATUS_ERROR(549, "接口返回状态码错误"),
  API_PARAM_INVALID(4001, "参数非法"),
  API_NO_PERMISSION(4002, "没有权限"),
  API_DATA_INVALID(5001, "数据非法"),
  JSON_ARRAY_PARSE_FAIL(6001, "JSON数组解析失败"),
  JSON_OBJECT_PARSE_FAIL(6002, "JSON对象解析失败");

  private final int value;
  private final String desc;
  private static HashSet<Integer> hashSet = new HashSet<>();

  static {
    hashSet.clear();
    ReturnStatus[] var0 = values();
    int var1 = var0.length;

    for (int var2 = 0; var2 < var1; ++var2) {
      ReturnStatus returnStatus = var0[var2];
      hashSet.add(returnStatus.getValue());
    }

  }


  ReturnStatus(int value, String desc) {

    this.value = value;
    this.desc = desc;
  }

  public int getValue() {

    return this.value;
  }

  public String getDesc() {

    return this.desc;
  }

  public static boolean isDefined(int value) {

    return hashSet.contains(value);
  }
}
