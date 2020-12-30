package com.jaymes.controller;


import com.jaymes.common.CodeMsg;
import com.jaymes.entity.MiaoshaOrder;
import com.jaymes.entity.MiaoshaUser;
import com.jaymes.entity.OrderInfo;
import com.jaymes.entity.vo.GoodsVo;
import com.jaymes.service.GoodsService;
import com.jaymes.service.MiaoshaService;
import com.jaymes.service.MiaoshaUserService;
import com.jaymes.service.OrderService;
import com.jaymes.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

  @Autowired
  MiaoshaUserService userService;

  @Autowired
  RedisService redisService;

  @Autowired
  GoodsService goodsService;

  @Autowired
  OrderService orderService;

  @Autowired
  MiaoshaService miaoshaService;

  @RequestMapping("/do_miaosha")
  public String list(Model model, MiaoshaUser user,
      @RequestParam("goodsId") long goodsId) {
    model.addAttribute("user", user);
    if (user == null) {
      return "login";
    }
    //判断库存
    GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    int stock = goods.getStockCount();
    if (stock <= 0) {
      model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
      return "miaosha_fail";
    }
    //判断是否已经秒杀到了
    MiaoshaOrder order = orderService
        .getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    if (order != null) {
      model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
      return "miaosha_fail";
    }
    //减库存 下订单 写入秒杀订单
    OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
    model.addAttribute("orderInfo", orderInfo);
    model.addAttribute("goods", goods);
    return "order_detail";
  }
}
