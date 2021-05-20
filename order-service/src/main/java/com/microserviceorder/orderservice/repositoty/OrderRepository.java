package com.microserviceorder.orderservice.repositoty;

import com.microserviceorder.orderservice.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
