package com.miao.product.service;

import com.miao.product.bean.Product;

public interface ProductService {
    Product getProductById(Long productId) throws InterruptedException;
}
