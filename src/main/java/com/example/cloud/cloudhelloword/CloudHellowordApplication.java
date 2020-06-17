package com.example.cloud.cloudhelloword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class CloudHellowordApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudHellowordApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
	    return "cloud hello";
    }

}
