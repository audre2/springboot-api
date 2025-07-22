package com.andrew.demo.springboot.api.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String name;
    private Double price;
}