package com.miao.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="order")
public class OrderProperties {

    String timeout;

    String autoConfirm;

    String databaseUrl;
}
