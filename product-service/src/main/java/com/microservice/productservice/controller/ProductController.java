package com.microservice.productservice.controller;

import java.util.List;

import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepositoy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductRepositoy productRepositoy;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll(){
    
        return productRepositoy.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product){
        productRepositoy.save(product);
    }
}
