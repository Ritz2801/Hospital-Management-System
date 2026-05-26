package dao;

import java.io.*;
import java.net.*;
import model.Patient;

public class PatientDAO {

    // SAVE PATIENT

    public void savePatient(
            Patient patient
    ){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/patients"
                    );

            HttpURLConnection con =
                    (HttpURLConnection)
                            url.openConnection();

            con.setRequestMethod(
                    "POST"
            );

            con.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            con.setDoOutput(
                    true
            );

            String json =

                    "{"

                    + "\"name\":\""
                    + patient.getName()
                    + "\","

                    + "\"age\":"
                    + patient.getAge()
                    + ","

                    + "\"disease\":\""
                    + patient.getDisease()
                    + "\""

                    + "}";

            OutputStream os =
                    con.getOutputStream();

            os.write(
                    json.getBytes("UTF-8")
            );

            os.flush();

            os.close();

            System.out.println(
                    "Save Response: "
                    + con.getResponseCode()
            );

            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }



    // GET PATIENTS

    public String getPatients(){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/patients"
                    );

            HttpURLConnection con =
                    (HttpURLConnection)
                            url.openConnection();

            con.setRequestMethod(
                    "GET"
            );

            BufferedReader br =
                    new BufferedReader(

                            new InputStreamReader(

                                    con.getInputStream()

                            )

                    );

            String line;

            StringBuilder response =
                    new StringBuilder();

            while(

                    (line=br.readLine())
                            !=null

            ){

                response.append(
                        line
                );

            }

            br.close();

            return response.toString();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return "";

    }



    // UPDATE PATIENT

    public void updatePatient(

            Patient patient,
            int id

    ){

        try{

            URL url =
                    new URL(

                            "http://localhost:8080/patients/"
                            + id

                    );

            HttpURLConnection con =
                    (HttpURLConnection)
                            url.openConnection();

            con.setRequestMethod(
                    "PUT"
            );

            con.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            con.setDoOutput(
                    true
            );

            String json =

                    "{"

                    + "\"name\":\""
                    + patient.getName()
                    + "\","

                    + "\"age\":"
                    + patient.getAge()
                    + ","

                    + "\"disease\":\""
                    + patient.getDisease()
                    + "\""

                    + "}";

            OutputStream os =
                    con.getOutputStream();

            os.write(
                    json.getBytes("UTF-8")
            );

            os.flush();

            os.close();

            System.out.println(
                    "Update Response: "
                    + con.getResponseCode()
            );

            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }



    // DELETE PATIENT

    public void deletePatient(
            int id
    ){

        try{

            URL url =
                    new URL(

                            "http://localhost:8080/patients/"
                            + id

                    );

            HttpURLConnection con =
                    (HttpURLConnection)
                            url.openConnection();

            con.setRequestMethod(
                    "DELETE"
            );

            System.out.println(

                    "Delete Response: "
                    + con.getResponseCode()

            );

            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}