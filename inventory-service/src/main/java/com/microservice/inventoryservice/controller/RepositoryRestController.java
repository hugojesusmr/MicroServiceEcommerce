package com.microservice.inventoryservice.controller;

import java.util.List;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/api/inventory")
public class RepositoryRestController {
    
  @Autowired
  private InventoryService inventoryService;  

  @GetMapping
  public List<Inventory> listInventory(){
     
        return inventoryService.listAllInventory(); 
    }
}
