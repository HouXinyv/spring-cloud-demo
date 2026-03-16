package com.miao.order.bean;

import com.miao.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickName;
    private List<Product> productList;
    private String Address;

    public void setAddress(String 尚硅谷) {
    }
}
