package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Payment;
import com.mb.repository.PaymentRepo;

@Service
public class PaymentServiceImpl implements PaymentService
{

	@Autowired
	private PaymentRepo paymentRepo;

	@Override
	public void Save(Payment payload)
	{
		paymentRepo.save(payload);
	}

}
