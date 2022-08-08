package com.mb.dto;

public class PlaceOrdersDto
{

	int quentity;

	public int getQuentity()
	{
		return quentity;
	}

	public void setQuentity(int quentity)
	{
		this.quentity = quentity;
	}

	public PlaceOrdersDto(int quentity)
	{
		super();
		this.quentity = quentity;
	}

	public PlaceOrdersDto()
	{
	}
}
