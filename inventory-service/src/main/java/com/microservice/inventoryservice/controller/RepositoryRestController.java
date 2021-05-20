package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class RepositoryRestController {
    
    private final InventoryRepository inventoryRepository;

    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode){
      Inventory inventory = 
        inventoryRepository
                .findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot Find Product by skuCOde" + skuCode));
      
      return inventory.getStock() > 0;
    }
}
