package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "id should not be Empty")
    private String id;
    @NotEmpty(message = "product id should not be Empty")
    private String productID;
    @NotEmpty(message = "merchant id should not be Empty")
    private String merchantID;
    @NotNull(message = "stock should not be null")
    @Min(value = 10,message = "stock should be at least 10")
    private int stock;
}
