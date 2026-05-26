package com.hms.hms_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.hms_backend.model.Appointment;

public interface AppointmentRepository
extends JpaRepository<
Appointment,
Integer
>{

}