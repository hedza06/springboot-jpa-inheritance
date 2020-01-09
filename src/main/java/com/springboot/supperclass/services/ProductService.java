package com.springboot.supperclass.services;

import com.springboot.supperclass.domain.Product;
import com.springboot.supperclass.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Storing new product
     *
     * @param product product payload
     * @return stored Product Object
     */
    public Product store(Product product) {
        return productRepository.save(product);
    }

    /**
     * Getting product by id
     *
     * @param id product identifier
     * @return Optional of Product Object
     */
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Getting all products
     *
     * @return List of Product Objects
     */
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
