package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long>
{

}
