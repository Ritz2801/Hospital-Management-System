package com.hms.hms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hms_backend.model.Doctor;
import com.hms.hms_backend.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository repository;


    public List<Doctor>
    getAllDoctors(){

        return repository.findAll();

    }


    public Doctor saveDoctor(
            Doctor doctor
    ){

        return repository.save(
                doctor
        );

    }


    public Doctor updateDoctor(

            Doctor doctor,
            int id

    ){

        doctor.setId(id);

        return repository.save(
                doctor
        );

    }


    public void deleteDoctor(
            int id
    ){

        repository.deleteById(
                id
        );

    }

}