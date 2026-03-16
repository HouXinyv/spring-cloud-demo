package com.miao.order.service;

import com.miao.order.bean.Order;

public interface OrderService {

    Order createOrder(Long productId,Long userId);
}
