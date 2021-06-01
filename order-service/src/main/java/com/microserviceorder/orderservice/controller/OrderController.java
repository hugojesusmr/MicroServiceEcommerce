package com.microserviceorder.orderservice.controller;

import java.util.UUID;
import java.util.function.Supplier;

import com.microserviceorder.orderservice.client.InventoryClient;
import com.microserviceorder.orderservice.dto.OrderDto;
import com.microserviceorder.orderservice.model.Order;
import com.microserviceorder.orderservice.repositoty.OrderRepository;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    
    private final OrderRepository orderRepository;
    
    private final InventoryClient inventoryClient;

    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    
    private final StreamBridge streamBridge;
    
    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");
        
        Supplier<Boolean> booleanSuplier = () -> 
        orderDto
                .getOrderLineItemsList()
                .stream()
                .allMatch(
                    orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
        boolean allProductsInStock = circuitBreaker.run(booleanSuplier,throwable -> handlerErrorCase());         
        
        if(allProductsInStock){
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("Sending Order Details to Notification Service");
            streamBridge.send("bindingName", order.getId());
            return "Order Place Successfully";
        }  else{
            return "Order Failed, One of the products in the order is not in stock";
        }           
        
    }

    private Boolean handlerErrorCase(){
        return false;
    }
}
