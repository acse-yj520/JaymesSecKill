package com.jaymes.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.jaymes.entity.MiaoshaOrder;
import com.jaymes.entity.MiaoshaUser;
import com.jaymes.entity.vo.GoodsVo;
import com.jaymes.service.GoodsService;
import com.jaymes.service.MiaoshaService;
import com.jaymes.service.OrderService;
import com.jaymes.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQReceiver {

		@Autowired
		RedisService redisService;
		
		@Autowired
		GoodsService goodsService;
		
		@Autowired
		OrderService orderService;
		
		@Autowired
		MiaoshaService miaoshaService;
		
		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receive(String message) {
			log.info("receive message:"+message);
			MiaoshaMessage mm  = JSON.parseObject(message, MiaoshaMessage.class);
			MiaoshaUser user = mm.getUser();
			long goodsId = mm.getGoodsId();

			GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	    	int stock = goods.getStockCount();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
	    	miaoshaService.miaosha(user, goods);
		}
	
//		@RabbitListener(queues=MQConfig.QUEUE)
//		public void receive(String message) {
//			log.info("receive message:"+message);
//		}

		@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
		public void receiveTopic1(String message) {
			log.info("topic queue1 message:"+message);
		}

		@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
		public void receiveTopic2(String message) {
			log.info("topic queue2 message:"+message);
		}

		@RabbitListener(queues=MQConfig.HEADER_QUEUE)
		public void receiveHeaderQueue(byte[] message) {
			log.info("header queue message:"+new String(message));
		}

		
}
