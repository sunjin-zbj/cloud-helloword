package com.example.cloud.cloudhelloword.controller;

import com.example.cloud.cloudhelloword.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: bhh
 * @Mail: sunjin@sudiyi.cn
 * @Date: 2020/5/28
 */
@RestController
@RequestMapping("/helloword")
@RefreshScope
public class HelloController {


    @LoadBalanced
    private final RestTemplate restTemplate;


    private final UserService userService;


    public HelloController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }


    @Value("${name}")
    private String name;

    @GetMapping("/cloud")
    public String helloHandler(){
        System.out.println(name);
        return "cloud";
    }

    @GetMapping("/user/hello")
    public String user(){
        return userService.userName("sj","header");
    }

    @GetMapping("/user/rest/{name}")
    public String user(@PathVariable String name){
        String restValue = restTemplate.getForObject("http://cloud-user/user/{name}", String.class, name);
        return restValue;
    }
}
