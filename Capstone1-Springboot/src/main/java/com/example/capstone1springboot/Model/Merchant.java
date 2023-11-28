package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "id should be not Empty")
    private String id;
    @NotEmpty(message = "name should be not Empty")
    @Size(min = 3,message = "should be not Empty")
    private String name;

}
