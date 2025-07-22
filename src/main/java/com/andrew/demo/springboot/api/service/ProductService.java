package com.andrew.demo.springboot.api.service;

import com.andrew.demo.springboot.api.entity.Product;
import com.andrew.demo.springboot.api.exception.InvalidProductException;
import com.andrew.demo.springboot.api.exception.ProductNotFoundException;
import com.andrew.demo.springboot.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAll() {
        log.info("Fetching all products");
        return repository.findAll();
    }

    public Product getById(Long id) {
        log.info("Fetching product with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    public Product create(Product product) {
        log.info("Creating product: {}", product);
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidProductException("Price must be greater than zero");
        }
        Product saved = repository.save(product);
        log.info("Product created with ID: {}", saved.getId());
        return saved;
    }

    public Product update(Long id, Product product) {
        log.info("Updating product with ID: {}", id);
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidProductException("Price must be greater than zero");
        }

        Product existing = getById(id);

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());

        Product updated = repository.save(existing);
        log.info("Product updated with ID: {}", updated.getId());
        return updated;
    }

    public void delete(Long id) {
        log.info("Deleting product with ID: {}", id);
        Product existing = getById(id);
        repository.delete(existing);
        log.info("Product with ID: {} deleted", id);
    }
}