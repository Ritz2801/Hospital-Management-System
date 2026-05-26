package com.hms.hms_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.hms_backend.model.Billing;

public interface BillingRepository extends JpaRepository<Billing,Integer>{

}