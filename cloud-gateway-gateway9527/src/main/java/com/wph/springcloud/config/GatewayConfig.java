package com.wph.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//编码方式的网关配置
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：https://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        //和yml文件联系着来理解
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_wph1",
                r -> r.path("/guonei")//这里的 / 一定不能少，我当时就是这样出错的
                        .uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

        //和yml文件联系着来理解
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_wph2",
                r -> r.path("/guoji")//这里的 / 一定不能少，我当时就是这样出错的
                        .uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }

}
