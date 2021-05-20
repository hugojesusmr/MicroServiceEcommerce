package com.microserviceorder.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/inventory")
@FeignClient(name = "inventory-service")
public interface InventoryClient {
    
    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode);
}
