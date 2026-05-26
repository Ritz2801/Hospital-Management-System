package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Doctor;

public class DoctorDAO {


    // SAVE

    public void saveDoctor(
            Doctor doctor
    ){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/doctors"
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

                    +"\"name\":\""
                    +doctor.getName()
                    +"\","

                    +"\"department\":\""
                    +doctor.getDepartment()
                    +"\","

                    +"\"availability\":\""
                    +doctor.getAvailability()
                    +"\""

                    +"}";


            OutputStream os =
                    con.getOutputStream();

            os.write(
                    json.getBytes(
                            "UTF-8"
                    )
            );

            os.flush();

            os.close();


            System.out.println(

                    "Response Code: "

                    +con.getResponseCode()

            );

            System.out.println(

                    "Doctor Saved"

            );


            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }



    // GET

    public String getDoctors(){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/doctors"
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



    // UPDATE

    public void updateDoctor(

            Doctor doctor,
            int id

    ){

        try{

            URL url =
                    new URL(

                            "http://localhost:8080/doctors/"
                            +id

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

                    +"\"name\":\""
                    +doctor.getName()
                    +"\","

                    +"\"department\":\""
                    +doctor.getDepartment()
                    +"\","

                    +"\"availability\":\""
                    +doctor.getAvailability()
                    +"\""

                    +"}";


            OutputStream os =
                    con.getOutputStream();

            os.write(
                    json.getBytes()
            );

            os.flush();

            os.close();


            System.out.println(

                    "Update Response: "

                    +con.getResponseCode()

            );


            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }



    // DELETE

    public void deleteDoctor(
            int id
    ){

        try{

            URL url =
                    new URL(

                            "http://localhost:8080/doctors/"
                            +id

                    );

            HttpURLConnection con =
                    (HttpURLConnection)
                            url.openConnection();

            con.setRequestMethod(
                    "DELETE"
            );


            System.out.println(

                    "Delete Response: "

                    +con.getResponseCode()

            );


            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}