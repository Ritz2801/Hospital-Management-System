package model;

public class Billing {

    private String id;
    private String patientName;
    private double amount;
    private String paymentMethod;
    private String status;


    public Billing(

            String id,
            String patientName,
            double amount,
            String paymentMethod,
            String status

    ){

        this.id=id;
        this.patientName=patientName;
        this.amount=amount;
        this.paymentMethod=paymentMethod;
        this.status=status;

    }


    public String getId(){

        return id;

    }


    public String getPatientName(){

        return patientName;

    }


    public double getAmount(){

        return amount;

    }


    public String getPaymentMethod(){

        return paymentMethod;

    }


    public String getStatus(){

        return status;

    }

}