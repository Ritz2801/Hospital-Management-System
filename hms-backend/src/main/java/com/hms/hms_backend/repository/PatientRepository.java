package com.hms.hms_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.hms_backend.model.Patient;

public interface PatientRepository
extends JpaRepository<Patient,Integer>{

}