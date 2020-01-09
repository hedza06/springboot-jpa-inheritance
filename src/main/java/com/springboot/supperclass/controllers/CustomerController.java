package com.springboot.supperclass.controllers;

import com.springboot.supperclass.domain.Customer;
import com.springboot.supperclass.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private CustomerService customerService;

    /**
     * Constructor dependency injector
     * @param customerService customer service
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    /**
     * Storing new customer | POST: /api/customer
     *
     * @param customer customer payload
     * @return ResponseEntity with Customer as response body and http status code 201 | 400
     */
    @PostMapping
    public ResponseEntity<Customer> store(@Valid @RequestBody Customer customer)
    {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().body(customer);
        }
        Customer storedCustomer = customerService.store(customer);
        return new ResponseEntity<>(storedCustomer, HttpStatus.CREATED);
    }

    /**
     * Get customer by unique identifier | GET : /api/customer/{id}
     *
     * @param id customer identifier
     * @return ResponseEntity with Customer as response body and http status code 200
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getOneById(@PathVariable Long id)
    {
        Optional<Customer> customerOptional = customerService.findOne(id);
        return customerOptional
            .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all customers | GET : /api/customer
     *
     * @return ResponseEntity with List of Customer objects as response body and http status code 200
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAll()
    {
        List<Customer> customers = customerService.findAll();
        return Optional.of(customers)
            .map(customerList -> new ResponseEntity<>(customerList, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
