package com.hms.hms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hms_backend.model.Billing;
import com.hms.hms_backend.repository.BillingRepository;

@Service
public class BillingService {

    @Autowired
    BillingRepository repository;



    public List<Billing> getAllBills(){

        return repository.findAll();

    }



    public Billing saveBill(
            Billing bill
    ){

        bill.setId(0);

        return repository.save(
                bill
        );

    }



    public Billing updateBill(

            Billing bill,
            int id

    ){

        bill.setId(id);

        return repository.save(
                bill
        );

    }



    public void deleteBill(
            int id
    ){

        repository.deleteById(
                id
        );

    }

}