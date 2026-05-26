package ui;

import dao.BillingDAO;
import model.Billing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.property.SimpleStringProperty;

public class BillingView {

    private VBox view;

    private TableView<String[]> table;

    private ObservableList<String[]> bills =
            FXCollections.observableArrayList();

    BillingDAO dao =
            new BillingDAO();


    public BillingView(){

        // LOAD FROM SPRING

        try{

            String data=
                    dao.getBills();

            System.out.println(data);

            if(data!=null &&
                    !data.equals("[]")){

                data=data.substring(
                        1,
                        data.length()-1
                );

                String[] rows=
                        data.split("\\},\\{");

                for(String row:rows){

                    row=row.replace("{","")
                            .replace("}","");

                    String id="";
                    String patient="";
                    String amount="";
                    String payment="";
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

                            case "amount":
                                amount=value;
                                break;

                            case "paymentMethod":
                                payment=value;
                                break;

                            case "status":
                                status=value;
                                break;

                        }

                    }

                    bills.add(

                            new String[]{

                                    id,
                                    patient,
                                    amount,
                                    payment,
                                    status

                            }

                    );

                }

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }



        Label title=
                new Label(
                        "Billing Management"
                );

        title.setStyle("""

                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);



        Button addBill=
                new Button(
                        "Generate Bill"
                );

        addBill.setStyle("""

                -fx-background-color:#5D8599;
                -fx-text-fill:white;
                -fx-font-weight:bold;

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



        TableColumn<String[],String> amountCol=
                new TableColumn<>("Amount");

        amountCol.setCellValueFactory(data->

                new SimpleStringProperty(
                        data.getValue()[2]
                )
        );



        TableColumn<String[],String> paymentCol=
                new TableColumn<>("Payment");

        paymentCol.setCellValueFactory(data->

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
                amountCol,
                paymentCol,
                statusCol

        );

        table.setItems(
                bills
        );



        addBill.setOnAction(e->{


            Dialog<ButtonType> dialog=
                    new Dialog<>();

            dialog.setTitle(
                    "Generate Bill"
            );


            TextField patient=
                    new TextField();

            patient.setPromptText(
                    "Patient Name"
            );


            TextField amount=
                    new TextField();

            amount.setPromptText(
                    "Amount"
            );


            ComboBox<String> payment=
                    new ComboBox<>();

            payment.getItems().addAll(

                    "Cash",
                    "UPI",
                    "Card"

            );

            payment.setValue(
                    "UPI"
            );


            ComboBox<String> status=
                    new ComboBox<>();

            status.getItems().addAll(

                    "Paid",
                    "Pending"

            );

            status.setValue(
                    "Pending"
            );


            VBox form=
                    new VBox(

                            15,

                            patient,
                            amount,
                            payment,
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

                            Billing bill=

                                    new Billing(

                                            "",

                                            patient.getText(),

                                            Double.parseDouble(
                                                    amount.getText()
                                            ),

                                            payment.getValue(),

                                            status.getValue()

                                    );


                            dao.saveBill(
                                    bill
                            );


                            // RELOAD PAGE

                            view.getChildren()
                                    .clear();

                            view.getChildren()
                                    .add(

                                            new BillingView()
                                                    .getView()

                                    );

                        }

                    });

        });



        HBox top=
                new HBox(

                        15,

                        addBill

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