package com.springboot.supperclass.controllers;

import com.springboot.supperclass.domain.Product;
import com.springboot.supperclass.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Storing new product | POST: /api/product
     *
     * @param product product payload
     * @return ResponseEntity with Product as response body and http status code 201 | 400
     */
    @PostMapping
    public ResponseEntity<Product> store(@Valid @RequestBody Product product)
    {
        if (product.getId() != null) {
            return ResponseEntity.badRequest().body(product);
        }
        Product storedProduct = productService.store(product);
        return new ResponseEntity<>(storedProduct, HttpStatus.CREATED);
    }

    /**
     * Get product by unique identifier | GET : /api/product/{id}
     *
     * @param id customer identifier
     * @return ResponseEntity with Product as response body and http status code 200
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getOneById(@PathVariable Long id)
    {
        Optional<Product> productOptional = productService.findOne(id);
        return productOptional
            .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all products | GET : /api/product
     *
     * @return ResponseEntity with List of Product objects as response body and http status code 200
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAll()
    {
        List<Product> products = productService.findAll();
        return Optional.of(products)
            .map(productList -> new ResponseEntity<>(productList, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
