//package com.xiaoweii.train.gateway.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
////增加过滤器进行校验实现拦截器的功能
//@Component
//public class Test1Filter implements GlobalFilter, Ordered {
//    private static final Logger LOG = LoggerFactory.getLogger(Test1Filter.class);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        LOG.info("Test1Filter");
//        return chain.filter(exchange);
////        return  exchange.getResponse().setComplete();
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
