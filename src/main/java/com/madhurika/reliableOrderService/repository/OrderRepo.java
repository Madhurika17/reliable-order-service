package com.madhurika.reliableOrderService.repository;

import com.madhurika.reliableOrderService.model.Order;
import com.madhurika.reliableOrderService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByUser(Optional<User> user);
}
