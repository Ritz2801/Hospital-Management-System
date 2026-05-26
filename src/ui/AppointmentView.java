package ui;

import dao.AppointmentDAO;
import model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.property.SimpleStringProperty;

public class AppointmentView {

    private VBox view;

    private TableView<String[]> table;

    private ObservableList<String[]> appointments =
            FXCollections.observableArrayList();

    AppointmentDAO dao =
            new AppointmentDAO();


    public AppointmentView(){


        // LOAD EXISTING DATA

        try{

            String data =
                    dao.getAppointments();

            System.out.println(data);

            data=data.replace("[","")
                    .replace("]","");

            if(!data.trim().isEmpty()){

                String[] rows=
                        data.split("\\},");

                for(String row:rows){

                    row=row.replace("{","")
                            .replace("}","");

                    String id="";
                    String patient="";
                    String doctor="";
                    String date="";
                    String status="";


                    String[] fields=
                            row.split(",");

                    for(String field:fields){

                        String[] pair=
                                field.split(":");

                        if(pair.length<2)
                            continue;

                        String key=
                                pair[0]
                                        .replace("\"","")
                                        .trim();

                        String value=
                                pair[1]
                                        .replace("\"","")
                                        .trim();


                        switch(key){

                            case "id":

                                id=value;
                                break;

                            case "patientName":

                                patient=value;
                                break;

                            case "doctorName":

                                doctor=value;
                                break;

                            case "date":

                                date=value;
                                break;

                            case "status":

                                status=value;
                                break;

                        }

                    }


                    appointments.add(

                            new String[]{

                                    id,
                                    patient,
                                    doctor,
                                    date,
                                    status

                            }

                    );

                }

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }



        Label title =
                new Label(
                        "Appointment Management"
                );

        title.setStyle("""

                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);


        Button addAppointment=
                new Button(
                        "Schedule"
                );

        addAppointment.setStyle("""

                -fx-background-color:#5D8599;

                -fx-text-fill:white;

                -fx-font-weight:bold;

                -fx-background-radius:10;

        """);


        table=
                new TableView<>();



        TableColumn<String[],String> idCol=
                new TableColumn<>("ID");

        idCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[0]
                )
        );


        TableColumn<String[],String> patientCol=
                new TableColumn<>("Patient");

        patientCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[1]
                )
        );


        TableColumn<String[],String> doctorCol=
                new TableColumn<>("Doctor");

        doctorCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[2]
                )
        );


        TableColumn<String[],String> dateCol=
                new TableColumn<>("Date");

        dateCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[3]
                )
        );


        TableColumn<String[],String> statusCol=
                new TableColumn<>("Status");

        statusCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[4]
                )
        );


        table.getColumns().addAll(

                idCol,
                patientCol,
                doctorCol,
                dateCol,
                statusCol

        );

        table.setItems(
                appointments
        );



        addAppointment.setOnAction(e->{


            Dialog<ButtonType> dialog=
                    new Dialog<>();

            dialog.setTitle(
                    "Schedule Appointment"
            );


            TextField patient=
                    new TextField();

            patient.setPromptText(
                    "Patient Name"
            );


            TextField doctor=
                    new TextField();

            doctor.setPromptText(
                    "Doctor Name"
            );


            DatePicker date=
                    new DatePicker();

            ComboBox<String> status=
                    new ComboBox<>();

            status.getItems().addAll(

                    "Scheduled",
                    "Completed",
                    "Cancelled"

            );

            status.setValue(
                    "Scheduled"
            );


            VBox form=
                    new VBox(

                            15,

                            patient,
                            doctor,
                            date,
                            status

                    );

            form.setPadding(
                    new Insets(20)
            );


            dialog.getDialogPane()
                    .setContent(
                            form
                    );

            dialog.getDialogPane()
                    .getButtonTypes()
                    .addAll(

                            ButtonType.OK,
                            ButtonType.CANCEL

                    );


            dialog.showAndWait()
                    .ifPresent(result->{


                if(result==
                        ButtonType.OK){

                    Appointment appointment=

                            new Appointment(

                                    "",

                                    patient.getText(),

                                    doctor.getText(),

                                    date.getValue()
                                            .toString(),

                                    status.getValue()

                            );

                    dao.saveAppointment(
                            appointment
                    );


                    appointments.add(

                            new String[]{

                                    String.valueOf(
                                            appointments.size()+1
                                    ),

                                    patient.getText(),

                                    doctor.getText(),

                                    date.getValue()
                                            .toString(),

                                    status.getValue()

                            }

                    );

                }

            });

        });



        HBox top=
                new HBox(

                        15,

                        addAppointment

                );


        view=
                new VBox(

                        25,

                        title,
                        top,
                        table

                );

        view.setPadding(
                new Insets(30)
        );

    }


    public Parent getView(){

        return view;

    }

}