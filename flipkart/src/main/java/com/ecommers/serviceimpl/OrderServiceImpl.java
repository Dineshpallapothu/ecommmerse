package com.ecommers.serviceimpl;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.OrderDto;

import com.ecommers.entities.Item;
import com.ecommers.entities.Order;
import com.ecommers.entities.Payment;
import com.ecommers.entities.Profile;
import com.ecommers.enums.OrderStatus;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.ItemRepo;
import com.ecommers.repositories.OrderRepo;
import com.ecommers.repositories.PaymentRepo;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    PaymentRepo paymentRepo;

//    @Autowired
//    Authentication authentication;

    @Override
    public List<OrderDto> getall()  {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        List<Order> orders = orderRepo.findByProfile_Id(profile.getId());
        if (orders.isEmpty()) {
            throw new NoValueException("No Orders available");
        } else {
            return orders.stream().map(OrderDto::new).toList();
        }


    }

    @Override
    public OrderDto remove(int id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Profile profile = profileRepo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        Order order;
        if (orderRepo.existsById(id)) {
             order=orderRepo.findById(id).get();
                    orderRepo.delete(order);
        }
        else {
                throw new NoValueException("No Orders Available");
        }
        return new OrderDto(order);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        String emal=SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile=profileRepo.findByEmail(emal).orElseThrow(()->new UsernameNotFoundException("Check your Crdentials"));
        Item item=itemRepo.findByName(orderDto.getName()).get();
        Payment payment=paymentRepo.findById(orderDto.getPaymentid()).orElseThrow(()-> new NoValueException("complete the payment"));
        Order order;
        String status= String.valueOf(payment.getStatus());
        if(status.equals("SUCCESS")){
            order = new Order();
            order.setPayment(payment);
            order.setItem(item);
            order.setProfile(profile);
            order.setName(orderDto.getName());
            order.setStatus(OrderStatus.READY_TO_SHIP);
            orderRepo.save(order);
        }
        else {
            throw new NoValueException("Payment not success");
        }



        return new OrderDto(order);
    }

    @Override
    public OrderDto update(int id,OrderDto orderDto) {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile=profileRepo.findByEmail(email).orElseThrow(()->new NothingFound("NO User available"));
        Item item=itemRepo.findByName(orderDto.getName()).get();
        Payment payment=paymentRepo.findById(orderDto.getPaymentid()).orElseThrow(()-> new NoValueException("complete the payment"));
        Order order;
        if(orderRepo.existsById(id)){
            order=orderRepo.findById(id).get();
            order.setStatus(OrderStatus.DISPACTCH);
        }
        else {
            throw new NothingFound("NO Orders available");
        }


        return new OrderDto(order);
    }
}
