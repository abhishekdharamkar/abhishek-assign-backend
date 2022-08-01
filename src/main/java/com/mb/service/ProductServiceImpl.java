
package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Product;
import com.mb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{

	@Autowired
	private ProductRepository repo;

	@Override
	public String save(Product product)
	{

		repo.save(product);
		return "save";
	}

	@Override
	public Product getProductList()
	{

		return repo.getProduct();
	}

}
