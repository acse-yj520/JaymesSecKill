package com.jaymes.rabbitmq;

import com.jaymes.entity.MiaoshaUser;
import lombok.Data;

@Data
public class MiaoshaMessage {
	private MiaoshaUser user;
	private long goodsId;
}
