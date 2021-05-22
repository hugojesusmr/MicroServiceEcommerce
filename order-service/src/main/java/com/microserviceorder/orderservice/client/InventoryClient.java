package com.microserviceorder.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "inventory-service")
@RequestMapping("/api/inventory")
public interface InventoryClient {
    
    @GetMapping(value = "/{id}")
    Boolean checkStock(@PathVariable("id") String skuCode);
}
