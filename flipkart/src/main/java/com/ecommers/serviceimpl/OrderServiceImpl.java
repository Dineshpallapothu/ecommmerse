package com.ecommers.serviceimpl;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.OrderDto;

import com.ecommers.repositories.OrderRepo;
import com.ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public List<OrderDto> getall() {
        return null;
    }

    @Override
    public OrderDto remove() {
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }
}
