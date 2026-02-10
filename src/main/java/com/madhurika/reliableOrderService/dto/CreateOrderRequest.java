package com.madhurika.reliableOrderService.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
}
