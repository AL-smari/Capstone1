package com.example.capstone1springboot.Controller;

import com.example.capstone1springboot.Model.Report;
import com.example.capstone1springboot.Service.MerchantService;
import com.example.capstone1springboot.Service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getReport(){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReports());
    }
    @PostMapping("/add")
    public ResponseEntity addReport(@Valid@RequestBody Report report , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

     if(reportService.addReport(report,merchantService.getMerchants())){
         return ResponseEntity.status(HttpStatus.OK).body("report added");
     }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");

    }

    @PutMapping("/update/{id}")
    public  ResponseEntity updateReport(@PathVariable String id , @Valid@RequestBody Report report , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        int response = reportService.updateReport(id,report,merchantService.getMerchants());

        if(response==1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
        }else if(response==2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");
        }else return ResponseEntity.status(HttpStatus.OK).body("report updated");

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReport(@PathVariable String id){
        if(reportService.deleteReport(id)){
            return ResponseEntity.status(HttpStatus.OK).body("report deleted");
        }else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

    }
}
