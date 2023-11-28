package com.example.capstone1springboot.Controller;

import com.example.capstone1springboot.Model.Category;
import com.example.capstone1springboot.Model.Product;
import com.example.capstone1springboot.Service.CategoryService;
import com.example.capstone1springboot.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid@RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if (productService.addProduct(categoryService.getCategories(), product)) {
            return ResponseEntity.status(HttpStatus.OK).body("Product added");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category id not found");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@Valid@RequestBody Product product,Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        int response = productService.updateProduct(categoryService.getCategories(),id,product);
        if(response==1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category id not found");

        }else if(response==2){
            return ResponseEntity.status(HttpStatus.OK).body("product updated");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        if(productService.deleteProduct(id)){
            return ResponseEntity.status(HttpStatus.OK).body("product deleted");
        }else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
}
