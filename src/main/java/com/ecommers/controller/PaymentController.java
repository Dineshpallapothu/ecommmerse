package com.ecommers.controller;
//
//import com.ecommers.entities.Payment;
import com.ecommers.dto.PaymentDto;
import com.ecommers.entities.Payment;
import com.ecommers.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<PaymentDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getall());
    }
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto paymentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(paymentDto));
    }
    @PutMapping("/upadte")
    public ResponseEntity<PaymentDto> modify(@RequestBody @Valid  PaymentDto paymentDto){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(paymentDto));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<PaymentDto> clear(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.remove(id));
    }
}
