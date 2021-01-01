package com.jaymes.entity.vo;

import com.jaymes.entity.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {

  private GoodsVo goods;
  private OrderInfo order;
}
