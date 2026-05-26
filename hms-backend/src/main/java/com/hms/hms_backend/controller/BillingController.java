package com.hms.hms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hms_backend.model.Billing;
import com.hms.hms_backend.service.BillingService;

@RestController
@RequestMapping("/billing")

public class BillingController {

    @Autowired
    BillingService service;


    @GetMapping
    public List<Billing>
    getBills(){

        return service.getAllBills();

    }


    @PostMapping
    public Billing saveBill(

            @RequestBody
            Billing bill

    ){

        return service.saveBill(
                bill
        );

    }


    @PutMapping("/{id}")
    public Billing updateBill(

            @RequestBody
            Billing bill,

            @PathVariable
            int id

    ){

        return service.updateBill(
                bill,
                id
        );

    }


    @DeleteMapping("/{id}")
    public void deleteBill(

            @PathVariable
            int id

    ){

        service.deleteBill(
                id
        );

    }

}