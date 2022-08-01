package com.mb.controller.dto;

public class PaymentInfoDto
{

	private String name;

	private String email;

	private int amount;

	public void setName(String name)
	{
		this.name = name;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public PaymentInfoDto(String name, String email, int amount)
	{
		super();
		this.name = name;
		this.email = email;
		this.amount = amount;
	}

	@Override
	public String toString()
	{
		return "PaymentInfoDto [name=" + name + ", email=" + email + ", amount=" + amount + "]";
	}

}
