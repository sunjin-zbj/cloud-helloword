package com.example.cloud.cloudhelloword.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: bhh
 * @Mail: sunjin@sudiyi.cn
 * @Date: 2020/6/16
 */
@Controller
public class RefreshController {

    @LoadBalanced
    private final RestTemplate restTemplate;

    @Autowired
    public RefreshController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/actuator/1-refresh")
    public ResponseEntity refresh(@RequestBody String str){
        System.out.println(str);
        new Thread(() ->{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("spring.cloud.bus.content-type","application/json");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null,httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://app/actuator/bus-refresh", request, String.class);
        }).start();

        return new ResponseEntity(HttpStatus.OK);
    }
}
