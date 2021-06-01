package com.microservice.inventoryservice.controller;

import java.util.List;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;
import com.microservice.inventoryservice.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/inventory")
public class RepositoryRestController {
    
  
  @Autowired
  private InventoryService inventoryService;

  @Autowired
  private InventoryRepository inventoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Inventory>> listInventory(){     
       List<Inventory> inventories = inventoryService.listAllInventory();
       if(inventories.isEmpty()){
         return ResponseEntity.noContent().build();
       }
         return ResponseEntity.ok(inventories); 
     }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Inventory inventory){
       inventoryService.createInventory(inventory);
    }  

        
    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable("skuCode") String skuCode){
     
     Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
        .orElseThrow(() -> new RuntimeException("Not Found Product"));

        return inventory.getStock() > 0;
    }
}
