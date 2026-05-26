package com.hms.hms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hms_backend.model.Patient;
import com.hms.hms_backend.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;


    public List<Patient> getAllPatients(){

        return repository.findAll();

    }


    public Patient savePatient(
            Patient patient
    ){

        return repository.save(
                patient
        );

    }


    public Patient updatePatient(
            Patient patient,
            int id
    ){

        patient.setId(id);

        return repository.save(
                patient
        );

    }


    public void deletePatient(
            int id
    ){

        repository.deleteById(
                id
        );

    }

}