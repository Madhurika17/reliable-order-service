package com.madhurika.reliableOrderService.controller;


import com.madhurika.reliableOrderService.dto.CreateOrderRequest;
import com.madhurika.reliableOrderService.dto.UpdateOrderStatusRequest;
import com.madhurika.reliableOrderService.model.Order;
import com.madhurika.reliableOrderService.model.User;
import com.madhurika.reliableOrderService.repository.OrderRepo;
import com.madhurika.reliableOrderService.repository.UserRepo;
import com.madhurika.reliableOrderService.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        //TODO: process POST request
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> orders = orderRepo.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepo.findById(id);
        if(order.isPresent()) return ResponseEntity.ok(order.get());
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if(!user.isPresent()) return ResponseEntity.notFound().build();
        else {
            List<Order> orders = orderRepo.findByUser(user);
            return ResponseEntity.ok(orders);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request) {
        Order updateOrder = orderService.updateOrderStatus(id, request.getStatus());
        return ResponseEntity.ok(updateOrder);
    }
    
    
}
