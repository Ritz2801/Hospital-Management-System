package com.hms.hms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hms_backend.model.Appointment;
import com.hms.hms_backend.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository repository;


    public List<Appointment>
    getAllAppointments(){

        return repository.findAll();

    }


    public Appointment saveAppointment(

            Appointment appointment

    ){

        return repository.save(
                appointment
        );

    }


    public Appointment updateAppointment(

            Appointment appointment,
            int id

    ){

        appointment.setId(
                id
        );

        return repository.save(
                appointment
        );

    }


    public void deleteAppointment(
            int id
    ){

        repository.deleteById(
                id
        );

    }

}