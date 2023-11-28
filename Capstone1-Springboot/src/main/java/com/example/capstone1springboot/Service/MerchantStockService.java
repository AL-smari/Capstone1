package com.example.capstone1springboot.Service;

import com.example.capstone1springboot.Model.Merchant;
import com.example.capstone1springboot.Model.MerchantStock;
import com.example.capstone1springboot.Model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks =new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }
    public int addMerchantStock(ArrayList<Product> products , ArrayList<Merchant> merchants ,MerchantStock merchantStock){
        int response =3;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(merchantStock.getProductID())){
                response =0;
                break;
            }else response =1;


        }if(response ==1){
            return response;
        }
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(merchantStock.getMerchantID())&&products.get(i).getId().equals(merchantStock.getProductID())){
                merchantStocks.add(merchantStock);
                response =2;
                break;

            }else response =3;

        }
        return response;


    }
    public int updateMerchantStock(ArrayList<Product> products , ArrayList<Merchant> merchants ,String id,MerchantStock merchantStock){
        int response=0;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(merchantStock.getProductID())){
                response=0;
                break;
            }else response =1;


        }


        if(response!=0){
            return response;
        }else {
            for (int i = 0; i < merchants.size(); i++) {
                if(merchants.get(i).getId().equals(merchantStock.getMerchantID())){
                    response =0;
                    break;
                }else response=2;

            }

            if(response!=0){
                return response;
            }else {
                for (int i = 0; i < merchantStocks.size(); i++) {
                    if(merchantStocks.get(i).getId().equals(id)){
                        merchantStocks.set(i,merchantStock);
                        response =4;
                        break;
                    }else response =3;

                }
            }
        }

        return response;

    }

    public boolean deleteMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }

        }
        return false;
    }


}
