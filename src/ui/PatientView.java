package ui;

import dao.PatientDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Patient;

public class PatientView {

    private VBox view;

    private TableView<String[]> table;

    private ObservableList<String[]> patients =
            FXCollections.observableArrayList();

    PatientDAO dao =
            new PatientDAO();


    public PatientView(){

        Label title =
                new Label(
                        "Patient Management"
                );

        title.setStyle("""
                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;
        """);


        TextField search =
                new TextField();

        search.setPromptText(
                "Search patient..."
        );


        Button addPatient =
                createButton(
                        "Add Patient"
                );

        Button updatePatient =
                createButton(
                        "Update"
                );

        Button deletePatient =
                createButton(
                        "Delete"
                );



        // TABLE

        table =
                new TableView<>();


        TableColumn<String[],String> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()[0]
                )
        );


        TableColumn<String[],String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()[1]
                )
        );


        TableColumn<String[],String> ageCol =
                new TableColumn<>("Age");

        ageCol.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()[2]
                )
        );


        TableColumn<String[],String> diseaseCol =
                new TableColumn<>("Disease");

        diseaseCol.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()[3]
                )
        );


        table.getColumns().addAll(

                idCol,
                nameCol,
                ageCol,
                diseaseCol

        );

        table.setItems(
                patients
        );

        table.setPrefHeight(
                400
        );



        // LOAD TABLE

        loadPatients();



        // ADD

        addPatient.setOnAction(e->{

            Dialog<ButtonType> dialog =
                    new Dialog<>();

            dialog.setTitle(
                    "Add Patient"
            );


            TextField name =
                    new TextField();

            name.setPromptText(
                    "Patient Name"
            );


            TextField age =
                    new TextField();

            age.setPromptText(
                    "Age"
            );


            TextField disease =
                    new TextField();

            disease.setPromptText(
                    "Disease"
            );


            VBox form =
                    new VBox(
                            15,
                            name,
                            age,
                            disease
                    );

            form.setPadding(
                    new Insets(20)
            );


            dialog.getDialogPane()
                    .setContent(form);

            dialog.getDialogPane()
                    .getButtonTypes()
                    .addAll(

                            ButtonType.OK,
                            ButtonType.CANCEL

                    );


            dialog.showAndWait()
                    .ifPresent(result -> {

                        if(result ==
                                ButtonType.OK){

                            Patient patient =

                                    new Patient(

                                            "",

                                            name.getText(),

                                            Integer.parseInt(
                                                    age.getText()
                                            ),

                                            disease.getText()

                                    );

                            dao.savePatient(
                                    patient
                            );

                            loadPatients();

                        }

                    });

        });



        // UPDATE

        updatePatient.setOnAction(e->{


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

            TextField age =
                    new TextField(
                            selected[2]
                    );

            TextField disease =
                    new TextField(
                            selected[3]
                    );


            Dialog<ButtonType> dialog =
                    new Dialog<>();


            VBox form =
                    new VBox(
                            15,
                            name,
                            age,
                            disease
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
                    .ifPresent(result -> {

                        if(result ==
                                ButtonType.OK){

                            Patient patient =

                                    new Patient(

                                            selected[0],

                                            name.getText(),

                                            Integer.parseInt(
                                                    age.getText()
                                            ),

                                            disease.getText()

                                    );

                            dao.updatePatient(

                                    patient,

                                    Integer.parseInt(
                                            selected[0]
                                    )

                            );

                            loadPatients();

                        }

                    });

        });



        // DELETE

        deletePatient.setOnAction(e -> {

            String[] selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if(selected==null){

                return;

            }

            dao.deletePatient(

                    Integer.parseInt(
                            selected[0]
                    )

            );

            loadPatients();

        });



        HBox top =
                new HBox(

                        20,

                        search,
                        addPatient,
                        updatePatient,
                        deletePatient

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



    private void loadPatients(){

        patients.clear();

        try{

            String data =
                    dao.getPatients();

            data =
                    data.replace("[","")
                            .replace("]","")
                            .replace("{","")
                            .replace("}","")
                            .replace("\"","");

            String[] records =
                    data.split("id:");

            for(int i=1;i<records.length;i++){

                String[] values =
                        records[i].split(",");


                String id =
                        values[0].trim();

                String name =
                        values[1]
                                .replace(
                                        "name:",
                                        ""
                                )
                                .trim();

                String age =
                        values[2]
                                .replace(
                                        "age:",
                                        ""
                                )
                                .trim();

                String disease =
                        values[3]
                                .replace(
                                        "disease:",
                                        ""
                                )
                                .trim();


                patients.add(

                        new String[]{

                                id,
                                name,
                                age,
                                disease

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