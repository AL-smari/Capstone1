package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "id should not be Empty")
    private String id;
    @NotEmpty(message = "name should not be Empty")
    @Size(min = 3,message = "name should not be more than 3 letter")
    private String name;
}
