package com.wph.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

//自定义全局过滤器

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************com in MyLogGateWayFilter:" + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //非法用户离开，实际的效果就是访问不到这个网页，连提示都没有
        if (uname == null){
            log.info("******用户名为null,非法用户！！！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();//完成退出
        }
        //合法用户放行
        return chain.filter(exchange);
    }

    //指定过滤器加载的顺序，返回值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
