package com.mb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.PaymentInfo;
import com.mb.repository.PaymentInfoRepo;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService
{

	@Autowired
	private PaymentInfoRepo paymentInfoRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void Save(PaymentInfo payload)
	{
		System.out.println("in service");
		// PaymentInfo paymentInfo = mapper.map(payload, PaymentInfo.class);
		System.out.println("yes");

		paymentInfoRepo.save(payload);
	}

}
