package com.example.capstone1springboot.Service;

import com.example.capstone1springboot.Model.MerchantStock;
import com.example.capstone1springboot.Model.Product;
import com.example.capstone1springboot.Model.Report;
import com.example.capstone1springboot.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }

        }
        return false;
    }


    public boolean addMoreStocks(ArrayList<MerchantStock> merchantStocks, String productID, String merchantID, int amount) {

        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getMerchantID().equals(merchantID) && merchantStocks.get(i).getProductID().equals(productID)) {

                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + amount);
                return true;
            }

        }
        return false;


    }

    public int userBuy(ArrayList<MerchantStock> merchantStocks, ArrayList<Product> products, String userID, String productID, String merchantID) {
        int response = 0;
        int userTemp = 0;
        int merchantTemp = 0;
        ArrayList<MerchantStock> purchase = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userID)) {
                response = 0;
                userTemp = i;
                break;
            } else response = 1;

        }
        if (response != 0) {
            return response;
        }
        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getMerchantID().equals(merchantID)) {

                response = 0;
                break;

            } else response = 2;
        }

        if (response != 0) {
            return response;
        }

        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getProductID().equals(productID) && merchantStocks.get(i).getMerchantID().equals(merchantID)) {

                response = 0;
                merchantTemp = i;
                break;
            } else response = 3;

        }
        if (response != 0) {
            return response;
        }

        if (merchantStocks.get(merchantTemp).getStock() >= 1) {
            response = 0;
        } else response = 4;
        if (response != 0) {
            return response;
        }

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productID))
                if (users.get(userTemp).getBalance() >= products.get(i).getPrice()) {
                    users.get(userTemp).setBalance(users.get(userTemp).getBalance() - products.get(i).getPrice());
                    merchantStocks.get(merchantTemp).setStock(merchantStocks.get(merchantTemp).getStock() - 1);
                    response = 0;
                    break;
                } else response = 5;

        }
        return response;

    }

    public int banMerchant(String id, String merchantID, ArrayList<Report> reports) {
        int response = 0;
        int userTemp = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                response = 0;
                userTemp = i;
                break;

            } else response = 1;

        }
        if (response != 0) {
            return response;
        }
        if (users.get(userTemp).getRole().equals("Admin")) {
            response = 0;
        } else response = 2;


        if (response != 0) {
            return response;
        }

        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getMerchantID().equals(merchantID)) {
                response = 0;
                return response;
            }else response =3;
        }



        return response;
    }

    public boolean getReports(String id){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)&&users.get(i).getRole().equals("Admin")){
                return true;
            }

        }
        return false;
    }
}
