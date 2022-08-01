package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.PaymentInfo;

@Repository
public interface PaymentInfoRepo extends JpaRepository<PaymentInfo, Long>
{

}
