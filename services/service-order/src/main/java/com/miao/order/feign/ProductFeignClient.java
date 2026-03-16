package com.miao.order.feign;

import com.miao.order.feign.fallback.ProductFeignClientFallback;
import com.miao.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value="service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    @GetMapping("/api/product/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
