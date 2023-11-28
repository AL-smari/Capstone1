package com.example.capstone1springboot.Controller;

import com.example.capstone1springboot.Model.Merchant;
import com.example.capstone1springboot.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(HttpStatus.OK).body("merchant added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id , @Valid@RequestBody Merchant merchant ,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }else if(merchantService.updateMerchant(id,merchant)){
            return ResponseEntity.status(HttpStatus.OK).body("merchant updated");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        if(merchantService.deleteMerchant(id)){
            return ResponseEntity.status(HttpStatus.OK).body("merchant deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }


}
