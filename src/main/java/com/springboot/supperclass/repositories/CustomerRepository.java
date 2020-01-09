package com.springboot.supperclass.repositories;

import com.springboot.supperclass.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
