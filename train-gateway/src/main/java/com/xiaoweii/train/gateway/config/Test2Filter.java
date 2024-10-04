package com.xiaoweii.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//增加过滤器进行校验实现拦截器的功能
@Component
public class Test2Filter implements GlobalFilter,Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(Test2Filter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOG.info("Test2Filter");
        return chain.filter(exchange);
//        return  exchange.getResponse().setComplete();
    }

    //多个过滤器集成Ordered类, 过滤器从小到大执行
    @Override
    public int getOrder() {
        return 1;
    }
}
