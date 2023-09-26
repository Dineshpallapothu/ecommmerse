package com.ecommers.controller;

import com.ecommers.dto.OrderDto;
import com.ecommers.dto.PaymentDto;
import com.ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getall()  {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getall());
    }
    @PostMapping("/save")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.create(orderDto));
    }
    @PutMapping("/upadte")
    public ResponseEntity<OrderDto> modify(@RequestParam("id")int id,@RequestBody OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id,orderDto));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<OrderDto> clear(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.remove(id));
    }
}
