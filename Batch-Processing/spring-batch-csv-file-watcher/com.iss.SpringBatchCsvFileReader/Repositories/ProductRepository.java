package com.iss.springbatchprocessing.Repositories;

import com.iss.springbatchprocessing.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
