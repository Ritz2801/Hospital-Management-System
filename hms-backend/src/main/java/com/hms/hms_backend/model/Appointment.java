package com.hms.hms_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")

public class Appointment {

    @Id
    @GeneratedValue(
            strategy=
            GenerationType.IDENTITY
    )
    private int id;

    private String patientName;

    private String doctorName;

    private String date;

    private String status;


    public Appointment(){

    }


    public int getId(){

        return id;

    }

    public void setId(
            int id
    ){

        this.id=id;

    }


    public String getPatientName(){

        return patientName;

    }

    public void setPatientName(
            String patientName
    ){

        this.patientName=
                patientName;

    }


    public String getDoctorName(){

        return doctorName;

    }

    public void setDoctorName(
            String doctorName
    ){

        this.doctorName=
                doctorName;

    }


    public String getDate(){

        return date;

    }

    public void setDate(
            String date
    ){

        this.date=date;

    }


    public String getStatus(){

        return status;

    }

    public void setStatus(
            String status
    ){

        this.status=status;

    }

}