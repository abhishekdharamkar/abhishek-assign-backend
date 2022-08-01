package com.mb.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product
{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String productname;

	private int productprice;

	@Column(columnDefinition = "TEXT")
	private String productDesc;

	@OneToMany(targetEntity = ImageUrl.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "pfk", referencedColumnName = "id")
	private List<ImageUrl> imageUrl;

	Product()
	{

	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getProductname()
	{
		return productname;
	}

	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	public int getProductprice()
	{
		return productprice;
	}

	public void setProductprice(int productprice)
	{
		this.productprice = productprice;
	}

	public String getProductDesc()
	{
		return productDesc;
	}

	public void setProductDesc(String productDesc)
	{
		this.productDesc = productDesc;
	}

	public List<ImageUrl> getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(List<ImageUrl> imageUrl)
	{
		this.imageUrl = imageUrl;
	}
}
