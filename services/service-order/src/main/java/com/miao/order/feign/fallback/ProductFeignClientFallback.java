package com.miao.order.feign.fallback;

import com.miao.order.feign.ProductFeignClient;
import com.miao.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("异常商品");
        product.setPrice(new BigDecimal(0));
        product.setNum(0);
        return product;
    }
}
