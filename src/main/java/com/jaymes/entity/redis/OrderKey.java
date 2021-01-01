package com.jaymes.entity.redis;

public class OrderKey extends BasePrefix {

  public OrderKey(String prefix) {
    super(prefix);
  }

  public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
