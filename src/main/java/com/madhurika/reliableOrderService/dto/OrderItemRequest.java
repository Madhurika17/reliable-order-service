package com.madhurika.reliableOrderService.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
