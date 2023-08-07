package com.geminibank.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.geminibank.apigateway.filter.AuthenticationFilter;
import com.geminibank.apigateway.filter.AuthenticationFilter.Config;

@Configuration
public class GatewayConfig {
	
	@Autowired
	AuthenticationFilter authenticationFilter;
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("accountsservice", r -> r
						.path("/account/**")
						.filters(f -> f.filter(authenticationFilter.apply(new Config())))
						.uri("lb://ACCOUNTSSERVICE"))
				.route("profileservice", r -> r
						.path("/profile/**")
						.filters(f -> f.filter(authenticationFilter.apply(new Config())))
						.uri("lb://PROFILESERVICE"))
				.route("authservice", r -> r
						.path("/auth/**")
						.uri("lb://AUTHSERVICE"))				
				.build();
						
	}

}
