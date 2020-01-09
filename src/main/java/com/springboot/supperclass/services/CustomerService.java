package com.springboot.supperclass.services;

import com.springboot.supperclass.domain.Customer;
import com.springboot.supperclass.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Storing new customer
     *
     * @param customer customer payload
     * @return stored Customer Object
     */
    public Customer store(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Get customer by id
     *
     * @param id customer identifier
     * @return Optional of Customer Object
     */
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Get all customers
     *
     * @return List of Customer Objects
     */
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
