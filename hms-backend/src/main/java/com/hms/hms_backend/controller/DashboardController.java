package com.hms.hms_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hms_backend.model.Billing;
import com.hms.hms_backend.repository.AppointmentRepository;
import com.hms.hms_backend.repository.BillingRepository;
import com.hms.hms_backend.repository.DoctorRepository;
import com.hms.hms_backend.repository.PatientRepository;

@RestController
@RequestMapping("/dashboard")

public class DashboardController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    BillingRepository billingRepository;


    @GetMapping
    public Map<String,Object> getDashboardData(){

        Map<String,Object> data =
                new HashMap<>();


        data.put(

                "patients",

                patientRepository.count()

        );


        data.put(

                "doctors",

                doctorRepository.count()

        );


        data.put(

                "appointments",

                appointmentRepository.count()

        );


        double revenue = 0;

        for(Billing bill:
                billingRepository.findAll()){

            revenue +=
                    bill.getAmount();

        }


        data.put(

                "revenue",

                revenue

        );

        return data;

    }

}