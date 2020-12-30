package com.jaymes.controller;

import com.jaymes.common.Result;
import com.jaymes.entity.MiaoshaUser;
import com.jaymes.service.MiaoshaUserService;
import com.jaymes.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
  MiaoshaUserService userService;
	
	@Autowired
  RedisService redisService;
	
    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model,MiaoshaUser user) {
        return Result.success(user);
    }
    
}
