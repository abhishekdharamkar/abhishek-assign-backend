package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query(value = "SELECT * FROM product u WHERE u.id = 1", nativeQuery = true)
	public Product getProduct();
}
