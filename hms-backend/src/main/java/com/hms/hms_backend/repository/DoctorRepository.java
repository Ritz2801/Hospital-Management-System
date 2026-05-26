package com.hms.hms_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.hms_backend.model.Doctor;

public interface DoctorRepository
extends JpaRepository<
        Doctor,
        Integer
>{}