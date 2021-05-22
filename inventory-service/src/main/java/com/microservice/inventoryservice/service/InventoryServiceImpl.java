package com.microservice.inventoryservice.service;

import java.util.List;

import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    
    @Override
    public List<Inventory> listAllInventory() {
        return inventoryRepository.findAll();
    }
    @Override
    public Inventory getInventory(String skuCode) {
        return  inventoryRepository.findBySkuCode(skuCode);
    }
}
