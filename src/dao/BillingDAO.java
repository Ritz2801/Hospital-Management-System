package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Billing;

public class BillingDAO {

    public String getBills(){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/billing"
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



    public void saveBill(
            Billing bill
    ){

        try{

            URL url=
                    new URL(
                            "http://localhost:8080/billing"
                    );

            HttpURLConnection con=
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


            String json=

                    "{"

                    +"\"patientName\":\""
                    +bill.getPatientName()
                    +"\","

                    +"\"amount\":"
                    +bill.getAmount()
                    +","

                    +"\"paymentMethod\":\""
                    +bill.getPaymentMethod()
                    +"\","

                    +"\"status\":\""
                    +bill.getStatus()
                    +"\""

                    +"}";


            System.out.println(json);

            OutputStream os=
                    con.getOutputStream();

            os.write(
                    json.getBytes("UTF-8")
            );

            os.flush();

            os.close();

            System.out.println(

                    "Response Code: "
                    +con.getResponseCode()

            );

            con.disconnect();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}