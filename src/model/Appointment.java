package model;

public class Appointment {

    private String id;

    private String patientName;

    private String doctorName;

    private String date;

    private String status;


    public Appointment(

            String id,
            String patientName,
            String doctorName,
            String date,
            String status

    ){

        this.id=id;
        this.patientName=patientName;
        this.doctorName=doctorName;
        this.date=date;
        this.status=status;

    }


    public String getId(){

        return id;

    }


    public String getPatientName(){

        return patientName;

    }


    public String getDoctorName(){

        return doctorName;

    }


    public String getDate(){

        return date;

    }


    public String getStatus(){

        return status;

    }

}