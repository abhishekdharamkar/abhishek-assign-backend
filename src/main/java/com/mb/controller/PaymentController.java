package com.mb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.PaymentInfo;
import com.mb.repository.ProductRepository;
import com.mb.service.PaymentInfoService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;

@RestController

public class PaymentController
{
	@Value("${Stripe.apiKey}")
	String StripeKey;

	@Value("${Stripe.endKey}")
	String endpointSecret;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	PaymentInfoService paymentInfoService;

	@Autowired
	private ModelMapper mapper;

	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/checkout")
	public Session checkout(@RequestBody int q) throws StripeException
	{

		int finalAmount = 299 * 100;
		List<Object> lineItems = new ArrayList<>();
		Map<String, Object> lineItem1 = new HashMap<>();
		lineItem1.put("quantity", q);
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

		return session;
	}

	@PostMapping("/stripe/events")
	public String handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader)
	{

		Event event;

		try
		{
			event = Webhook.constructEvent(
					payload, sigHeader, endpointSecret);
		}
		catch (SignatureVerificationException e)
		{
			// Invalid signature
			return "";
		}

		if (sigHeader == null)
		{
			return "";
		}

		if (endpointSecret != null && sigHeader != null)
		{

			EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
			StripeObject stripeObject = null;
			if (dataObjectDeserializer.getObject().isPresent())
			{
				stripeObject = dataObjectDeserializer.getObject().get();

			}
			else
			{

			}

			if ("charge.succeeded".equals(event.getType()))
			{

				Charge charge = (Charge) stripeObject;

				String name = charge.getBillingDetails().getName();

				String email = charge.getBillingDetails().getEmail();

				long total = charge.getAmount();

				long subtotal = total / 100;

				PaymentInfo paymentInfoDto = new PaymentInfo(name, email, (int) subtotal);
				paymentInfoService.Save(paymentInfoDto);
				System.out.println(paymentInfoDto);

			}
		}
		return "";
	}

}
