package edu.psu.se411.lab._3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import edu.psu.se411.lab._3.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>,
														PagingAndSortingRepository<Product, Integer> {
	
	
}
