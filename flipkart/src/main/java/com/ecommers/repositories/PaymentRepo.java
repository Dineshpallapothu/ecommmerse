package com.ecommers.repositories;

import com.ecommers.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {

    List<Payment> findByProfile_Id(int id);
    Payment findByProfile_IdAndId(int pid,int id);
}
