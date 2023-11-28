package com.example.capstone1springboot.Service;

import com.example.capstone1springboot.Model.Category;
import com.example.capstone1springboot.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<Product>products=new ArrayList<>();


    public ArrayList<Product> getProducts(){
        return products;
    }

    public boolean addProduct(ArrayList<Category> categories, Product product){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(product.getCategoryID())){
                products.add(product);
                return true;

            }

        }
        return false;

    }

    public int updateProduct(ArrayList<Category>categories , String id , Product product){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(product.getCategoryID())==false){
              return 1;

            }else break;

        }
        for (int i = 0; i < products.size(); i++) {

            if(products.get(i).getId().equals(id)){
                products.set(i,product);
                return 2;
            }

        }

        return 3;

    }
    public boolean deleteProduct(String id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }

        }
        return false;
    }



}
