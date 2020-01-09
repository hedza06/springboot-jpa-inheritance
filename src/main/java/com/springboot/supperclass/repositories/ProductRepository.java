package com.springboot.supperclass.repositories;

import com.springboot.supperclass.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
