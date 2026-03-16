package com.miao.product.service.impl;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import com.miao.product.bean.Product;
import com.miao.product.service.ProductService;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) throws InterruptedException {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果-"+productId);
        product.setNum(2);

//        try {
//            TimeUnit.SECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        TimeUnit.SECONDS.sleep(100);
        return product;
    }
}
