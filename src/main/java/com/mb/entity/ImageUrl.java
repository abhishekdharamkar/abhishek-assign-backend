package com.mb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image_url")
public class ImageUrl
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String url1;
	private String url2;
	private String url3;
	private String url4;

	public long getId()
	{
		return id;
	}

	public String getUrl1()
	{
		return url1;
	}

	public String getUrl2()
	{
		return url2;
	}

	public String getUrl3()
	{
		return url3;
	}

	public String getUrl4()
	{
		return url4;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setUrl1(String url1)
	{
		this.url1 = url1;
	}

	public void setUrl2(String url2)
	{
		this.url2 = url2;
	}

	public void setUrl3(String url3)
	{
		this.url3 = url3;
	}

	public void setUrl4(String url4)
	{
		this.url4 = url4;
	}
}
