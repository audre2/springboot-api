package com.andrew.demo.springboot.api.mapper;

import com.andrew.demo.springboot.api.dto.ProductDTO;
import com.andrew.demo.springboot.api.dto.ProductResponseDTO;
import com.andrew.demo.springboot.api.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        if (product == null) return null;
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public List<ProductResponseDTO> toResponseDTOList(List<Product> products) {
        return products.stream()
                       .map(this::toResponseDTO)
                       .toList();
    }
}
