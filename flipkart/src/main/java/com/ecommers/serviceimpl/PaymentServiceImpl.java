package com.ecommers.serviceimpl;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.PaymentDto;

import com.ecommers.entities.Cart;
import com.ecommers.entities.Payment;
import com.ecommers.entities.Profile;
import com.ecommers.enums.PaymentStatus;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.CartRepo;
import com.ecommers.repositories.PaymentRepo;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    CartRepo cartRepo;
//    @Autowired
//    Authentication authentication;

    @Override
    public List<PaymentDto> getall() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("check your credentials"));
        List<Payment> payments = paymentRepo.findByProfile_Id(profile.getId());
        if (payments.isEmpty()) {
            throw new NoValueException("NO payments");
        } else {
            return payments.stream().map(PaymentDto::new).toList();
        }


    }

    @Override
    public PaymentDto remove(int id) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("check your credentials"));
        Payment payment = null;
        if ( profile != null) {
            payment = paymentRepo.findByProfile_IdAndId(profile.getId(), id);
        } else {
            throw new NoValueException("No Payment Present");
        }
        return new PaymentDto(payment);
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("check your credentials"));
        Cart cart = cartRepo.findById(paymentDto.getCartid()).orElseThrow(() -> new NoValueException("Enter Valid cartid"));
        Payment payment = null;
        if ( profile != null) {
            if (cartRepo.existsById(paymentDto.getCartid())) {
                payment = new Payment();
                payment.setCardno(paymentDto.getCardno());
                payment.setCart(cart);
                payment.setProfile(profile);
                payment.setStatus(PaymentStatus.SUCCESS);
                paymentRepo.save(payment);
            } else {
                throw new NoValueException("Cart is empty");
            }

        } else {
            throw new UsernameNotFoundException("Please Login or check your credentials");
        }

        return new PaymentDto(payment);
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("check your credentials"));
        Cart cart=cartRepo.findById(paymentDto.getCartid()).orElseThrow(()->new NothingFound("Cart not available"));
        Payment payment = null;
        if ( profile != null) {
            if (cartRepo.existsById(paymentDto.getCartid())) {
                payment = new Payment();
                payment.getCart().setId(paymentDto.getCartid());
                payment.setStatus(PaymentStatus.PENDING);
                payment.setProfile(profile);
            }

        }
        return new PaymentDto(payment);
    }
}
