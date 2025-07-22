package com.andrew.demo.springboot.api.service;

import com.andrew.demo.springboot.api.entity.Product;
import com.andrew.demo.springboot.api.exception.InvalidProductException;
import com.andrew.demo.springboot.api.exception.ProductNotFoundException;
import com.andrew.demo.springboot.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnAllProducts() {
        Product product = Product.builder()
                .id(1L)
                .name("Notebook")
                .price(3500.0)
                .build();

        when(repository.findAll()).thenReturn(List.of(product));
        List<Product> products = service.getAll();
        assertEquals(1, products.size());
        assertEquals("Notebook", products.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnProductWhenIdExists() {
        Product product = Product.builder()
                .id(1L)
                .name("Notebook")
                .price(3500.0)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Product result = service.getById(1L);
        assertNotNull(result);
        assertEquals("Notebook", result.getName());
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void shouldCreateProduct() {
        Product product = Product.builder()
                .name("Notebook")
                .price(3500.0)
                .build();

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Notebook")
                .price(3500.0)
                .build();

        when(repository.save(product)).thenReturn(savedProduct);
        Product result = service.create(product);
        assertNotNull(result.getId());
        assertEquals("Notebook", result.getName());
    }

    @Test
    void shouldThrowWhenPriceInvalidOnCreate() {
        Product product = Product.builder()
                .name("Notebook")
                .price(0.0)
                .build();
        assertThrows(InvalidProductException.class, () -> service.create(product));
    }

    @Test
    void shouldThrowWhenPriceNullOnCreate() {
        Product product = Product.builder()
                .name("Notebook")
                .price(null)
                .build();
        assertThrows(InvalidProductException.class, () -> service.create(product));
    }

    @Test
    void shouldUpdateProduct() {
        Product existing = Product.builder()
                .id(1L)
                .name("Notebook")
                .price(3500.0)
                .build();

        Product updateData = Product.builder()
                .name("Notebook Gamer")
                .price(4000.0)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);
        Product updated = service.update(1L, updateData);
        assertEquals("Notebook Gamer", updated.getName());
        assertEquals(4000.0, updated.getPrice());
    }

    @Test
    void shouldThrowWhenPriceInvalidOnUpdate() {
        Product updateData = Product.builder()
                .name("Notebook Gamer")
                .price(0.0)
                .build();

        assertThrows(InvalidProductException.class, () -> service.update(1L, updateData));
    }

    @Test
    void shouldDeleteProduct() {
        Product product = Product.builder()
                .id(1L)
                .name("Notebook")
                .price(3500.0)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        service.delete(1L);
        verify(repository, times(1)).delete(product);
    }
}