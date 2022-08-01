package com.mb;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.stripe.Stripe;

@SpringBootApplication
public class StripGatewayApplication
{

	@Value("${Stripe.apiKey}")
	String StripeKey;

	@PostConstruct
	public void setup()
	{
		Stripe.apiKey = StripeKey;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(StripGatewayApplication.class, args);
	}

}
