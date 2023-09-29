package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getall();

    PaymentDto  remove(int id);

    PaymentDto create( PaymentDto paymentDto);

    PaymentDto update( PaymentDto paymentDto);
}
