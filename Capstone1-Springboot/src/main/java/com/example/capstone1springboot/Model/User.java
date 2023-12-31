package com.example.capstone1springboot.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id should not be Empty")
    private String id;
    @NotEmpty(message = "username should not be Empty")
    @Size(min = 5 , message = "username should not be less than 5")
    private String username;
    @NotEmpty(message = "password should not be Empty")
    @Size(min = 6,message = "password should not be less than 6")
    @Pattern(regexp = "[0-9A-Za-z]+",message = "password should have characters and digits")
    private String password;
    @NotEmpty(message = "Email should not be Empty")
    @Email(message = "must be valid Email")
    private String Email;
    @NotEmpty(message = "role should not be Empty")
    @Pattern(regexp = "Admin|Customer",message = "role must be Admin or Customer")
    private String role;
    @NotNull(message = "balance should not be Empty")
    @Positive(message = "balance should be positive")
    private double balance;
    private ArrayList<MerchantStock>purchase=new ArrayList<>();
}
