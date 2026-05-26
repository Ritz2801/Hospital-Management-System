package com.hms.hms_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY
    )
    private int id;

    private String name;

    private String department;

    private String availability;


    public Doctor(){

    }


    public int getId(){

        return id;

    }

    public void setId(
            int id
    ){

        this.id=id;

    }


    public String getName(){

        return name;

    }

    public void setName(
            String name
    ){

        this.name=name;

    }


    public String getDepartment(){

        return department;

    }

    public void setDepartment(
            String department
    ){

        this.department=department;

    }


    public String getAvailability(){

        return availability;

    }

    public void setAvailability(
            String availability
    ){

        this.availability=availability;

    }

}