package com.example.cloud.cloudhelloword.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.util.annotation.Nullable;

@FeignClient("cloud-user")
public interface UserService{

    @RequestMapping("/user/{name}")
    public String userName(@PathVariable String  name,@Nullable @RequestHeader(name ="head") String head);
}
