package com.ecommers.serviceimpl;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.PaymentDto;

import com.ecommers.repositories.PaymentRepo;
import com.ecommers.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Override
    public ResponseEntity<List<PaymentDto>> getall() {
        return null;
    }

    @Override
    public ResponseEntity<PaymentDto> remove() {
        return null;
    }

    @Override
    public ResponseEntity<PaymentDto> create(PaymentDto cartDto) {
        return null;
    }

    @Override
    public ResponseEntity<PaymentDto> update(PaymentDto cartDto) {
        return null;
    }
}
