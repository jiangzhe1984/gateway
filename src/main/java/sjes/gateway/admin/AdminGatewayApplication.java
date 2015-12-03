package sjes.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableZuulProxy
@EnableFeignClients("client.api")
public class AdminGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminGatewayApplication.class, args);
	}
}