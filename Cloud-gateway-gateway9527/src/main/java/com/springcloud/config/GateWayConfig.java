package com.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator constomRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route1",r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
    @Bean
    public RouteLocator constomRouteLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route2",r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
