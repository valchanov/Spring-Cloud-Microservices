package com.bookstore.bookratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RefreshScope
@EnableHystrixDashboard
public class BookRatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRatingServiceApplication.class, args);
	}

}
