package com.jaymes.entity.vo;

import com.jaymes.entity.Goods;
import java.util.Date;
import lombok.Data;


@Data
public class GoodsVo extends Goods {
	private Double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;

}
