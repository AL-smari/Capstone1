package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {

    @NotEmpty(message = "id should not be null ")
    private String id;
    @NotEmpty(message = "merchant id should not be null ")
    private String merchantID;
    @Size(max = 100,message = "message cant be more than 100")
    private String message;
        
}
