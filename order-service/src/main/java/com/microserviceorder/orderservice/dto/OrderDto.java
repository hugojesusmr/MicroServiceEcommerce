package com.microserviceorder.orderservice.dto;

import java.util.List;

import com.microserviceorder.orderservice.model.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private List<OrderLineItems> orderLineItemsList;
}
