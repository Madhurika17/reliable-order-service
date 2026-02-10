package com.madhurika.reliableOrderService.dto;

import com.madhurika.reliableOrderService.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
