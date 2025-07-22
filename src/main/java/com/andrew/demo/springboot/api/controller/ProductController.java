package com.andrew.demo.springboot.api.controller;

import com.andrew.demo.springboot.api.dto.ProductDTO;
import com.andrew.demo.springboot.api.dto.ProductResponseDTO;
import com.andrew.demo.springboot.api.entity.Product;
import com.andrew.demo.springboot.api.mapper.ProductMapper;
import com.andrew.demo.springboot.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> products = mapper.toResponseDTOList(service.getAll());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable("id") Long id) {
        Product product = service.getById(id);
        return ResponseEntity.ok(mapper.toResponseDTO(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        Product savedProduct = service.create(mapper.toEntity(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(savedProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        Product updatedProduct = service.update(id, product);
        return ResponseEntity.ok(mapper.toResponseDTO(updatedProduct));
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
