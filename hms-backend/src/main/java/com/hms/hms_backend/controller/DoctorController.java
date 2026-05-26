package com.hms.hms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hms.hms_backend.model.Doctor;
import com.hms.hms_backend.service.DoctorService;

@RestController
@RequestMapping("/doctors")

public class DoctorController {

    @Autowired
    DoctorService service;


    @GetMapping
    public List<Doctor>
    getDoctors(){

        return service
                .getAllDoctors();

    }


    @PostMapping
    public Doctor saveDoctor(

            @RequestBody
            Doctor doctor

    ){

        return service
                .saveDoctor(
                        doctor
                );

    }


    @PutMapping("/{id}")
    public Doctor updateDoctor(

            @RequestBody
            Doctor doctor,

            @PathVariable
            int id

    ){

        return service
                .updateDoctor(
                        doctor,
                        id
                );

    }


    @DeleteMapping("/{id}")
    public void deleteDoctor(

            @PathVariable
            int id

    ){

        service.deleteDoctor(
                id
        );

    }

}