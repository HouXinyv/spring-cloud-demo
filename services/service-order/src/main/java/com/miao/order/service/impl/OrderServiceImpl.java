package com.miao.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.miao.order.bean.Order;
import com.miao.order.feign.ProductFeignClient;
import com.miao.order.service.OrderService;
import com.miao.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    @SentinelResource(value="createOrder",blockHandler = "createOrderFallback")
    public Order createOrder(Long productId, Long userId) {
//        Product productFromRemote = getProductFromRemote(productId);
        Order order = new Order();
        order.setId(1L);

        Product productFromRemote = productFeignClient.getProductById(productId);
        // 总金额
        order.setTotalAmount(productFromRemote.getPrice());
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("尚硅谷");
        //远程查询商品列表
        order.setProductList(Arrays.asList(productFromRemote));
        return order;
    }

    public Order createOrderFallback(Long productId, Long userId, BlockException e){
        Order order = new Order();
        order.setAddress("xxx");
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(0L);
        order.setNickName("xxx");
        return order;
    }

    private Product getProductFromRemote(Long productId){
        String url = "http://service-product/product/"+productId;
        return restTemplate.getForObject(url,Product.class);
    }

}
