package com.ecommers.Util;

import com.ecommers.entities.Payment;
import com.ecommers.entities.Profile;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NotFound;
import com.ecommers.repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class Util {
@Autowired
 private  ProfileRepo profileRepo;




    public  Profile getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile=profileRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Login"));
        return profile;
    }
    public  Payment checkStatus(Payment payment){
        String status=String.valueOf(payment.getStatus());
        return switch (status) {
            case "PENDING" -> throw new NotFound("Payment in pending please wait");
            case "FAILED" -> throw new NoValueException("Payment failed");
            case "SUCCESS" -> payment;
            default -> null;
        };
    }


}
