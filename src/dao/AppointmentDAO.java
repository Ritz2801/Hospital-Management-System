package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Appointment;

public class AppointmentDAO {


public String getAppointments(){

try{

URL url=
new URL(
"http://localhost:8080/appointments"
);

HttpURLConnection con=
(HttpURLConnection)
url.openConnection();

con.setRequestMethod(
"GET"
);

BufferedReader br=
new BufferedReader(

new InputStreamReader(
con.getInputStream()
)

);

String line;

StringBuilder response=
new StringBuilder();

while((line=br.readLine())!=null){

response.append(line);

}

br.close();

return response.toString();

}

catch(Exception e){

e.printStackTrace();

}

return "";

}



public void saveAppointment(
Appointment appointment
){

System.out.println(
"saved"
);

}



public void updateAppointment(
Appointment appointment,
int id
){

System.out.println(
"updated"
);

}



public void deleteAppointment(
int id
){

System.out.println(
"deleted"
);

}

}