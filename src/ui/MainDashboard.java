package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainDashboard extends Application {

    public static Stage primaryStage;

    private static StackPane contentPane;

    @Override
    public void start(Stage stage){

        primaryStage = stage;

        Scene scene = new Scene(

                new LoginView().getView(),
                1450,
                850

        );

        stage.setTitle(
                "Hospital Management System"
        );

        stage.setScene(
                scene
        );

        stage.show();

    }



    public static void openDashboard(

            String role

    ){

        BorderPane root =
                new BorderPane();

        contentPane =
                new StackPane();


        contentPane.getChildren().add(

                new DashboardView()
                        .getView()

        );



        Button dashboardBtn =
                new Button(
                        "Dashboard"
                );

        Button patientBtn =
                new Button(
                        "Patients"
                );

        Button doctorBtn =
                new Button(
                        "Doctors"
                );

        Button appointmentBtn =
                new Button(
                        "Appointments"
                );

        Button billingBtn =
                new Button(
                        "Billing"
                );

        Button reportsBtn =
                new Button(
                        "Reports"
                );

        Button logoutBtn =
                new Button(
                        "Logout"
                );



        // ROLE ACCESS

        if(role.equals("Doctor")){

            billingBtn.setVisible(false);

            reportsBtn.setVisible(false);

            doctorBtn.setVisible(false);

        }


        if(role.equals("Reception")){

            doctorBtn.setVisible(false);

            reportsBtn.setVisible(false);

            appointmentBtn.setVisible(false);

        }



        Button[] buttons = {

                dashboardBtn,
                patientBtn,
                doctorBtn,
                appointmentBtn,
                billingBtn,
                reportsBtn,
                logoutBtn

        };


        for(Button b:buttons){

            b.setPrefWidth(
                    180
            );

            b.setStyle("""

                    -fx-background-color:#5f89a1;
                    -fx-text-fill:white;
                    -fx-font-size:14px;
                    -fx-font-weight:bold;
                    -fx-background-radius:10;

            """);

        }



        VBox sidebar =
                new VBox(

                        20,

                        dashboardBtn,
                        patientBtn,
                        doctorBtn,
                        appointmentBtn,
                        billingBtn,
                        reportsBtn,
                        logoutBtn

                );



        sidebar.setPadding(

                new Insets(
                        20
                )

        );


        sidebar.setStyle("""

                -fx-background-color:#324760;

        """);



        dashboardBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new DashboardView()
                                        .getView()

                        )

        );



        patientBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new PatientView()
                                        .getView()

                        )

        );



        doctorBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new DoctorView()
                                        .getView()

                        )

        );



        appointmentBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new AppointmentView()
                                        .getView()

                        )

        );



        billingBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new BillingView()
                                        .getView()

                        )

        );



        reportsBtn.setOnAction(e ->

                contentPane
                        .getChildren()
                        .setAll(

                                new ReportsView()
                                        .getView()

                        )

        );



        logoutBtn.setOnAction(e -> {

            Scene loginScene =
                    new Scene(

                            new LoginView()
                                    .getView(),

                            1450,
                            850

                    );

            primaryStage
                    .setScene(
                            loginScene
                    );

        });



        root.setLeft(
                sidebar
        );

        root.setCenter(
                contentPane
        );



        Scene dashboardScene =
                new Scene(

                        root,
                        1450,
                        850

                );


        primaryStage.setScene(
                dashboardScene
        );

    }



    public static void main(

            String[] args

    ){

        launch(
                args
        );

    }

}