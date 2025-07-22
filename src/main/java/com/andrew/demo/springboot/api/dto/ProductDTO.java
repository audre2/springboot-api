package com.andrew.demo.springboot.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull(message = "Price must be provided")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;
}