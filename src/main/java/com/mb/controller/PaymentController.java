package com.mb.controller;

import static com.mb.constant.UrlConstants.BASE_URL;
import static com.mb.constant.UrlConstants.CHECKOUT;
import static com.mb.constant.UrlConstants.WEBHOOK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.dto.PlaceOrdersDto;
import com.mb.entity.Payment;
import com.mb.exception.CustomException;
import com.mb.repository.ProductRepository;
import com.mb.response.SuccResponse;
import com.mb.service.PaymentService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

@RestController
@RequestMapping(BASE_URL)
public class PaymentController
{
	@Value("${Stripe.apiKey}")
	String StripeKey;

	@Value("${Stripe.endKey}")
	String endpointSecret;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	PaymentService paymentService;

	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping(CHECKOUT)
	public String checkout(@RequestBody PlaceOrdersDto placeOrdersDto) throws StripeException
	{
		return paymentService.PlaceOrder(placeOrdersDto);
	}

	@PostMapping(WEBHOOK)
	public ResponseEntity<SuccResponse> handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader)
	{

		Event event = null;

		try
		{
			event = Webhook.constructEvent(
					payload, sigHeader, endpointSecret);
		}
		catch (SignatureVerificationException e)
		{
			// Invalid signature

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

				Payment payment = new Payment(name, email, (int) subtotal);
				paymentService.Save(payment);

				SuccResponse response = SuccResponse.getInstance();
				response.setMessage("Success");
				response.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<SuccResponse>(response, HttpStatus.OK);

			}
			else
			{
				throw new CustomException("Error");
			}
		}
		return null;

	}

}
