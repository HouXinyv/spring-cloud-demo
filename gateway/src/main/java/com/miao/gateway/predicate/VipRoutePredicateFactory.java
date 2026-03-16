package com.miao.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// 自定义的断言工厂，名字必须是xxxRoutePredicateFactory
// 模仿QueryRoutePredicateFactory
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {

    public static final String PARAM_KEY = "param";

    public static final String REGEXP_KEY = "value";

    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    //指定短写法时候的顺序
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY, REGEXP_KEY);
    }

    // 真正的逻辑
    @Override
    public Predicate<ServerWebExchange> apply(VipRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                ServerHttpRequest request = exchange.getRequest();

                String first = request.getQueryParams().getFirst(config.param);

                return StringUtils.hasText(first) && first.equals(config.value);
            }
        };
    }

    // 可以配置的参数
    @Validated
    public static class Config {

        @NotEmpty
        private String param;

        @NotEmpty
        public String value;

        @NotEmpty
        public @NotEmpty String getParam() {
            return param;
        }

        public void setParam(@NotEmpty String param) {
            this.param = param;
        }

        public @NotEmpty String getValue() {
            return value;
        }

        public void setValue(@NotEmpty String value) {
            this.value = value;
        }

    }
}
