package com.ecommers.serviceimpl;

import com.ecommers.Util.Util;
import com.ecommers.dto.OrderDto;
import com.ecommers.entities.Item;
import com.ecommers.entities.Order;
import com.ecommers.entities.Payment;
import com.ecommers.entities.Profile;
import com.ecommers.enums.OrderStatus;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NotFound;
import com.ecommers.repositories.ItemRepo;
import com.ecommers.repositories.OrderRepo;
import com.ecommers.repositories.PaymentRepo;
import com.ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    Util util;

    @Override
    public List<OrderDto> getall() {
        Profile profile = util.getCurrentUser();
        List<Order> orders = orderRepo.findByProfile_Id(profile.getId());
        if (orders.isEmpty()) {
            throw new NoValueException("No Orders available");
        } else {
            return orders.stream().map(OrderDto::new).toList();
        }

    }

    @Override
    public OrderDto remove(int id) {

        Profile profile = util.getCurrentUser();
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

        Profile profile=util.getCurrentUser();
        Item item=itemRepo.findByName(orderDto.getName()).get();
        Payment payment=paymentRepo.findById(orderDto.getPaymentid()).orElseThrow(()-> new NoValueException("complete the payment"));
        Order order;
        Payment payment1= util.checkStatus(payment);
            order = new Order();
            order.setPayment(payment);
            order.setItem(item);
            order.setProfile(profile);
            order.setName(orderDto.getName());
            order.setStatus(OrderStatus.READY_TO_SHIP);
            orderRepo.save(order);
        return new OrderDto(order);
    }

    @Override
    public OrderDto update(int id,OrderDto orderDto) {

       Profile profile=util.getCurrentUser();
        Item item=itemRepo.findByName(orderDto.getName()).get();
        Payment payment=paymentRepo.findById(orderDto.getPaymentid()).orElseThrow(()-> new NoValueException("complete the payment"));
        Order order;
        if(orderRepo.existsById(id)){
            order=orderRepo.findById(id).get();
            order.setStatus(OrderStatus.DISPACTCH);
        }
        else {
            throw new NotFound("No Orders available");
        }
        return new OrderDto(order);
    }
}
