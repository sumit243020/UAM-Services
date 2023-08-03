package com.uamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UamServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UamServicesApplication.class, args);
	}

}
