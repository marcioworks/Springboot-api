package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
