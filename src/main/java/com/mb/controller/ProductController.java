package com.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.service.ProductService;

@RestController

public class ProductController
{

	@Autowired
	private ProductService productService;

	@CrossOrigin(origins = {"http://localhost:3000"})
	@GetMapping("/productList")
	public Product GetProduct()
	{

		return productService.getProductList();
	}

	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/buynow")
	public String buynow()
	{
		System.out.println("success");
		return "save";
	}

	@PostMapping("/product")
	public String save(@RequestBody Product p)
	{

		return productService.save(p);
	}
}
