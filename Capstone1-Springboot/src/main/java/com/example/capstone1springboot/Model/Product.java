package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "id must not be Empty")
    private String id;
    @NotEmpty(message = "must not be Empty")
    @Size(min = 3 ,message = "name must be more than 3 letters")
    private String name;
    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive number")
    private double price;
    @NotEmpty(message = "category ID must not be Emptys")
    private String categoryID;
}
