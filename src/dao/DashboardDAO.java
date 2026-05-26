package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DashboardDAO {

    public String getDashboardData(){

        try{

            URL url =
                    new URL(
                            "http://localhost:8080/dashboard"
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

}