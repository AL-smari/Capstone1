package com.example.capstone1springboot.Service;

import com.example.capstone1springboot.Model.Merchant;
import com.example.capstone1springboot.Model.Report;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportService {

    ArrayList<Report>reports = new ArrayList<>();


    public ArrayList<Report> getReports(){
        return reports;
    }

    public boolean addReport(Report report, ArrayList<Merchant>merchants){


        for (int i = 0; i < merchants.size(); i++) {

            if(merchants.get(i).getId().equals(report.getMerchantID())){
                reports.add(report);
                return true;
            }

        }

        return false;
    }

    public int updateReport(String id,Report report,ArrayList<Merchant>merchants){
        int response=0;
        for (int i = 0; i < reports.size(); i++) {
            if(reports.get(i).getId().equals(id)){
                response =0;
                break;

            }else response=1;

        }
        if(response!=0){
            return response;
        }

        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(report.getMerchantID())){
                response=0;
                reports.set(i,report);
                return response;

            }else response =2;

        }
        return response;
    }

    public boolean deleteReport(String id){
        for (int i = 0; i < reports.size(); i++) {
            if(reports.get(i).getId().equals(id)){
                reports.remove(i);
                return true;
            }

        }
        return false;
    }
}
