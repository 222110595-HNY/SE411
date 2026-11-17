package edu.psu.se411.lab._3.service;

import org.springframework.stereotype.Service;

import edu.psu.se411.lab._3.model.Product;
import edu.psu.se411.lab._3.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}
	
}
