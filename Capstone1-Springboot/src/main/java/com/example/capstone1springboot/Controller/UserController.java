package com.example.capstone1springboot.Controller;

import com.example.capstone1springboot.Model.Merchant;
import com.example.capstone1springboot.Model.Report;
import com.example.capstone1springboot.Model.User;
import com.example.capstone1springboot.Service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;
private final MerchantStockService merchantStockService;
private final ProductService productService;
private final ReportService reportService;
private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getUser(){

    return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id , @Valid@RequestBody User user ,Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }else if(userService.updateUser(id, user)){
            return ResponseEntity.status(HttpStatus.OK).body("User updated");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
    //Q11 endPoint add to stock
    @PutMapping("/addMoreStocks/{productID}/{merchantID}/{amount}")
    public ResponseEntity addMoreStocks(@PathVariable String productID , @PathVariable String merchantID , @PathVariable int amount){

        if(userService.addMoreStocks(merchantStockService.getMerchantStock(),productID,merchantID,amount)){
            return ResponseEntity.status(HttpStatus.OK).body("Stock added");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

    }
    //Q12 endPoint user Buy
    @PutMapping("/userBuy/{userID}/{productID}/{merchantID}")
    public ResponseEntity userBuy(@PathVariable String userID , @PathVariable String productID,@PathVariable String merchantID){
    int response = userService.userBuy(merchantStockService.getMerchantStock(),productService.getProducts(),userID,productID,merchantID);
    if(response==1){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }else if(response==2){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");

    }else if(response==3){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product id not found");
    }else if(response==4){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no product in stock");
    }
    else if(response==5){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user dont have enough money");
    }else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("buy completed");

    }

    @PostMapping("/addReport")
    public ResponseEntity reportMerchant(@RequestBody Report report){

        if(reportService.addReport(report,merchantService.getMerchants())){
            return ResponseEntity.status(HttpStatus.OK).body("report added");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");

    }

    @DeleteMapping("/deleteMerchant/{id}/{merchantID}")
    public ResponseEntity banMerchant(@PathVariable String id,@PathVariable String merchantID){

        int response = userService.banMerchant(id,merchantID,reportService.getReports());

        if(response==1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user id not found");
        }else if (response==2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user id not Admin");
        }else  if (response==3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");
        }else
            merchantService.deleteMerchant(merchantID);
            return ResponseEntity.status(HttpStatus.OK).body("merchant banned");
    }

    @GetMapping("/getReport/{id}")
    public ResponseEntity getReport(@PathVariable String id){
        if(userService.getReports(id)){
            return ResponseEntity.status(HttpStatus.OK).body(reportService.getReports());
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");

    }



}
