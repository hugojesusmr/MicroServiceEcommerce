package com.microservice.inventoryservice.service;

import java.util.List;

import com.microservice.inventoryservice.model.Inventory;

public interface InventoryService {
    public List<Inventory> listAllInventory();
    public Inventory getInventory(String skuCode);
    
}
