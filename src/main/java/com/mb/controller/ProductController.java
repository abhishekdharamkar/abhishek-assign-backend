package com.mb.controller;

import static com.mb.constant.UrlConstants.PRODUCT;
import static com.mb.constant.UrlConstants.PRODUCTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.service.ProductService;;

@RestController

public class ProductController
{

	@Autowired
	private ProductService productService;

	@CrossOrigin(origins = {"http://localhost:3000"})
	@GetMapping(PRODUCTS)
	public ResponseEntity<?> getproduct()
	{
		Product product = productService.getProductList();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/* This Method Is Use To Save The Multiple product In future */
	@PostMapping(PRODUCT)
	public String save(@RequestBody Product p)
	{

		return productService.save(p);
	}
}
