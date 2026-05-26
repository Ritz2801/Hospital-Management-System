package ui;

import dao.DoctorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Doctor;

public class DoctorView {

    private VBox view;

    private TableView<String[]> table;

    private ObservableList<String[]> doctors =
            FXCollections.observableArrayList();

    DoctorDAO dao =
            new DoctorDAO();


    public DoctorView(){

        Label title =
                new Label(
                        "Doctor Management"
                );

        title.setStyle("""
                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;
        """);


        Button addDoctor =
                createButton(
                        "Add Doctor"
                );

        Button updateDoctor =
                createButton(
                        "Update"
                );

        Button deleteDoctor =
                createButton(
                        "Delete"
                );



        table =
                new TableView<>();


        TableColumn<String[],String> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[0]
                )
        );


        TableColumn<String[],String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[1]
                )
        );


        TableColumn<String[],String> departmentCol =
                new TableColumn<>("Department");

        departmentCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[2]
                )
        );


        TableColumn<String[],String> availabilityCol =
                new TableColumn<>("Availability");

        availabilityCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[3]
                )
        );



        table.getColumns().addAll(

                idCol,
                nameCol,
                departmentCol,
                availabilityCol

        );

        table.setItems(
                doctors
        );

        table.setPrefHeight(
                400
        );



        loadDoctors();



        // ADD

        addDoctor.setOnAction(e->{


            Dialog<ButtonType> dialog =
                    new Dialog<>();

            dialog.setTitle(
                    "Add Doctor"
            );


            TextField name =
                    new TextField();

            name.setPromptText(
                    "Doctor Name"
            );


            TextField department =
                    new TextField();

            department.setPromptText(
                    "Department"
            );


            TextField availability =
                    new TextField();

            availability.setPromptText(
                    "Availability"
            );


            VBox form =
                    new VBox(

                            15,

                            name,
                            department,
                            availability

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

                    Doctor doctor=

                            new Doctor(

                                    "",

                                    name.getText(),

                                    department.getText(),

                                    availability.getText()

                            );


                    dao.saveDoctor(
                            doctor
                    );

                    loadDoctors();

                }

            });

        });



        // UPDATE

        updateDoctor.setOnAction(e->{


            String[] selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if(selected==null){

                return;

            }


            TextField name =
                    new TextField(
                            selected[1]
                    );


            TextField department =
                    new TextField(
                            selected[2]
                    );


            TextField availability =
                    new TextField(
                            selected[3]
                    );


            Dialog<ButtonType> dialog =
                    new Dialog<>();


            VBox form =
                    new VBox(

                            15,

                            name,
                            department,
                            availability

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

                    Doctor doctor=

                            new Doctor(

                                    selected[0],

                                    name.getText(),

                                    department.getText(),

                                    availability.getText()

                            );


                    dao.updateDoctor(

                            doctor,

                            Integer.parseInt(
                                    selected[0]
                            )

                    );

                    loadDoctors();

                }

            });

        });



        // DELETE

        deleteDoctor.setOnAction(e->{


            String[] selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if(selected==null){

                return;

            }


            dao.deleteDoctor(

                    Integer.parseInt(
                            selected[0]
                    )

            );

            loadDoctors();

        });



        HBox top =
                new HBox(

                        20,

                        addDoctor,
                        updateDoctor,
                        deleteDoctor

                );


        view =
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



    private void loadDoctors(){

        doctors.clear();

        try{

            String data =
                    dao.getDoctors();

            if(data==null ||
                    data.isEmpty()){

                return;

            }

            data =
                    data.replace("[","")
                            .replace("]","")
                            .replace("{","")
                            .replace("}","")
                            .replace("\"","");

            String[] records =
                    data.split("id:");

            for(

                    int i=1;

                    i<records.length;

                    i++

            ){

                String[] values =
                        records[i]
                                .split(",");

                if(values.length<4){

                    continue;

                }


                String id =
                        values[0]
                                .trim();

                String name =
                        values[1]
                                .replace(
                                        "name:",
                                        ""
                                )
                                .trim();


                String availability =
                        values[2]
                                .replace(
                                        "availability:",
                                        ""
                                )
                                .trim();

                String department =
                        values[3]
                                .replace(
                                        "department:",
                                        ""
                                )
                                .trim();


                doctors.add(

                        new String[]{

                                id,
                                name,
                                department,
                                availability

                        }

                );

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }



    private Button createButton(
            String text
    ){

        Button btn =
                new Button(
                        text
                );

        btn.setStyle("""

                -fx-background-color:#5D8599;
                -fx-text-fill:white;
                -fx-font-weight:bold;
                -fx-background-radius:10;

        """);

        return btn;

    }


    public Parent getView(){

        return view;

    }

}