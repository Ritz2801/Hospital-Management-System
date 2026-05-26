package com.hms.hms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hms.hms_backend.model.Appointment;
import com.hms.hms_backend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")

public class AppointmentController {

    @Autowired
    AppointmentService service;


    @GetMapping
    public List<Appointment>
    getAppointments(){

        return service
                .getAllAppointments();

    }


    @PostMapping
    public Appointment saveAppointment(

            @RequestBody
            Appointment appointment

    ){

        return service
                .saveAppointment(
                        appointment
                );

    }


    @PutMapping("/{id}")
    public Appointment updateAppointment(

            @RequestBody
            Appointment appointment,

            @PathVariable
            int id

    ){

        return service
                .updateAppointment(
                        appointment,
                        id
                );

    }


    @DeleteMapping("/{id}")
    public void deleteAppointment(

            @PathVariable
            int id

    ){

        service.deleteAppointment(
                id
        );

    }

}