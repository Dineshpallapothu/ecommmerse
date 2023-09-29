package com.ecommers.controller;

import com.ecommers.dto.OrderDto;
import com.ecommers.dto.PaymentDto;
import com.ecommers.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<OrderDto>> getall()  {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getall());
    }
    @PostMapping("/order")
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.create(orderDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> modify(@PathVariable("id") int id,@RequestBody  @Valid  OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id,orderDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> clear(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.remove(id));
    }
}
