package com.microservice.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping(value = "/api/test")
@RefreshScope
@Data
public class TestController {
    
    @Value("${config.key}")
    private String name;

    @GetMapping
    public String name(){
        return name;
    }
}
