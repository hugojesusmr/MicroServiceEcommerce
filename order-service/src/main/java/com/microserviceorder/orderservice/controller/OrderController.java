package com.microserviceorder.orderservice.controller;

import java.util.UUID;

import com.microserviceorder.orderservice.client.InventoryClient;
import com.microserviceorder.orderservice.dto.OrderDto;
import com.microserviceorder.orderservice.model.Order;
import com.microserviceorder.orderservice.repositoty.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")

public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InventoryClient inventoryClient;
    
    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){

        boolean allProductsInStock = 
        orderDto
                .getOrderLineItemsList()
                .stream()
                 .allMatch(
                     orderLineItems -> inventoryClient.isInStock(orderLineItems.getSkuCode()));
        if(allProductsInStock){
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);

            return "Order Place Successfully";
        }  else{
            return "Order Failed, One od the products in the order is not in stock";
        }           
        
    }
}
