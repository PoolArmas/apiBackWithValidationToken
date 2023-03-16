package com.spring.jpa.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.jpa.h2.model.Product;

/**
 * Class Repository
 * 
 * @author 56949
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findBySku(String sku);
}
