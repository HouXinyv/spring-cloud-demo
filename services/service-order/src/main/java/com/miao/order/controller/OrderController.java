package com.miao.order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.miao.order.bean.Order;
import com.miao.order.properties.OrderProperties;
import com.miao.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin
@Slf4j
@RestController
//@RefreshScope
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config(){
        return "order.timeout="+orderProperties.getTimeout()
                +", order.auto-confirm="+orderProperties.getAutoConfirm()
                +", order.database-url="+orderProperties.getDatabaseUrl();
    }

    //创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId){
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    @GetMapping("/seckill")
    public Order seckill(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId){
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    @GetMapping("/writeDb")
    public String writeDb(){
        return "writedb success...";
    }

    @GetMapping("/readDb")
    public String readDb(){
        log.info("readDb");
        return "readdb success...";
    }




}
