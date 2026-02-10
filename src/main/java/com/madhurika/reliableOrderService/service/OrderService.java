package com.madhurika.reliableOrderService.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhurika.reliableOrderService.dto.CreateOrderRequest;
import com.madhurika.reliableOrderService.dto.OrderItemRequest;
import com.madhurika.reliableOrderService.enums.OrderStatus;
import com.madhurika.reliableOrderService.model.Order;
import com.madhurika.reliableOrderService.model.OrderItem;
import com.madhurika.reliableOrderService.model.User;
import com.madhurika.reliableOrderService.repository.OrderRepo;
import com.madhurika.reliableOrderService.repository.OrderItemRepo;
import com.madhurika.reliableOrderService.repository.UserRepo;


@Service
public class OrderService {
    
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private UserRepo userRepo;

    public Order createOrder(CreateOrderRequest request){
        User user = userRepo.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found!"));

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(OrderItemRequest item: request.getItems()){
            BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setAmount(totalAmount);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepo.save(order);

        for(OrderItemRequest i: request.getItems()){
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProductName(i.getProductName());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());

            orderItemRepo.save(item);
        }

        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus){
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found!"));
        
        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }
}
