package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentService {
    ResponseEntity<List<PaymentDto>> getall();

    ResponseEntity<PaymentDto>  remove();

    ResponseEntity<PaymentDto> create( PaymentDto cartDto);

    ResponseEntity<PaymentDto> update( PaymentDto cartDto);
}
