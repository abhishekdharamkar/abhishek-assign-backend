package com.mb.service;

import com.mb.dto.PlaceOrdersDto;
import com.mb.entity.Payment;
import com.stripe.exception.StripeException;

public interface PaymentService
{

	public void Save(Payment paymentInfoDto);

	public String PlaceOrder(PlaceOrdersDto PlaceOrdersDto) throws StripeException;
}
