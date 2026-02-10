package com.madhurika.reliableOrderService.repository;

import com.madhurika.reliableOrderService.model.OrderItem;
import com.madhurika.reliableOrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    
    List<OrderItem> findByOrder(Order order);
}
