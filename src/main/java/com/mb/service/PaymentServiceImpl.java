package com.mb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.mb.dto.PlaceOrdersDto;
import com.mb.entity.Payment;
import com.mb.repository.PaymentRepo;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

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

	private static Gson gson = new Gson();

	@Override
	public String PlaceOrder(PlaceOrdersDto PlaceOrdersDto) throws StripeException
	{
		int finalAmount = 299 * 100;// conversion
		List<Object> lineItems = new ArrayList<>();
		Map<String, Object> lineItem1 = new HashMap<>();
		lineItem1.put("quantity", PlaceOrdersDto.getQuentity());
		lineItem1.put("name", "Abhishek");
		lineItem1.put("amount", finalAmount);
		lineItem1.put("currency", "inr");
		lineItems.add(lineItem1);
		Map<String, Object> params = new HashMap<>();
		params.put(
				"success_url",
				"http://localhost:3000/success");
		params.put(
				"cancel_url",
				"http://localhost:3000/cancle");

		params.put("line_items", lineItems);
		params.put("mode", "payment");

		Session session = Session.create(params);

		return gson.toJson(session);

	}

}
