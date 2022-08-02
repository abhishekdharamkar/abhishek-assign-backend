package com.mb.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mb.response.ErrorResponse;
import com.stripe.exception.StripeException;

@RestControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(value = CustomException.class)
	public ErrorResponse customizedException(CustomException ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.CONFLICT.value(), "Bad Request");
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ErrorResponse nullPointerException(NullPointerException ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something Went wrong");
	}

	@ExceptionHandler(value = StripeException.class)
	public ErrorResponse stripeException(StripeException ex)
	{
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getLocalizedMessage(), HttpStatus.CONFLICT.value(), "Something Went wrong");
	}
}
