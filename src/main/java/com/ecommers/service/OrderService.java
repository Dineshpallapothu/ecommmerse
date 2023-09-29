package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;
import java.util.List;

public interface OrderService {
    List<OrderDto> getall() ;

    OrderDto  remove(int id);

    OrderDto create( OrderDto orderDto);

    OrderDto update( int id,OrderDto orderDto);
}
