package com.mb.exception;

public class CustomException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CustomException(String message, Throwable t)
	{
		super(message, t);
	}

	public CustomException(String message)
	{
		super(message);
	}

}
