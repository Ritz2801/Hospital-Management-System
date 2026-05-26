package com.hms.hms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hms.hms_backend.model.Patient;
import com.hms.hms_backend.service.PatientService;

@RestController
@RequestMapping("/patients")

public class PatientController {

    @Autowired
    PatientService service;


    @GetMapping
    public List<Patient> getPatients(){

        return service.getAllPatients();

    }


    @PostMapping
    public Patient savePatient(

            @RequestBody
            Patient patient

    ){

        return service.savePatient(
                patient
        );

    }


    @PutMapping("/{id}")
    public Patient updatePatient(

            @RequestBody
            Patient patient,

            @PathVariable
            int id

    ){

        return service.updatePatient(

                patient,
                id

        );

    }


    @DeleteMapping("/{id}")
    public void deletePatient(

            @PathVariable
            int id

    ){

        service.deletePatient(
                id
        );

    }

}