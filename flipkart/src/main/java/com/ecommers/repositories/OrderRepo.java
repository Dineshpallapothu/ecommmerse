package com.ecommers.repositories;

import com.ecommers.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByProfile_Id(int id);
}
