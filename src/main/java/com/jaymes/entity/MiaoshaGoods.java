package com.jaymes.entity;

import java.util.Date;
import lombok.Data;

@Data
public class MiaoshaGoods {

  private Long id;
  private Long goodsId;
  private Integer stockCount;
  private Date startDate;
  private Date endDate;
}
