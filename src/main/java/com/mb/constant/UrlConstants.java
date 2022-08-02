package com.mb.constant;

public class UrlConstants
{
	private UrlConstants()
	{
		throw new IllegalStateException("Constants class.can't instatiate");
	}

	public static final String CHECKOUT = "/checkout";
	public static final String WEBHOOK = "/stripe/events";

	public static final String PRODUCT = "/save";
	public static final String PRODUCTS = "/productList";

}
