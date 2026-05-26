package ui;

import dao.DashboardDAO;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {

    private VBox view;

    DashboardDAO dao =
            new DashboardDAO();

    public DashboardView(){

        String data =
                dao.getDashboardData();

        String patients="0";
        String doctors="0";
        String appointments="0";
        String revenue="0";

        try{

            data=data.replace("{","")
                    .replace("}","");

            String[] fields=
                    data.split(",");

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

                    case "patients":
                        patients=value;
                        break;

                    case "doctors":
                        doctors=value;
                        break;

                    case "appointments":
                        appointments=value;
                        break;

                    case "revenue":
                        revenue=value;
                        break;

                }

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }


        Label title=
                new Label(
                        "Dashboard Overview"
                );

        title.setStyle("""

                -fx-font-size:36px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);



        HBox cards=
                new HBox(

                        30,

                        createCard(
                                patients,
                                "Patients"
                        ),

                        createCard(
                                doctors,
                                "Doctors"
                        ),

                        createCard(
                                appointments,
                                "Appointments"
                        ),

                        createCard(
                                "₹"+revenue,
                                "Revenue"
                        )

                );



        Label recent=
                new Label(
                        "Recent Activity"
                );

        recent.setStyle("""

                -fx-font-size:24px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);


        VBox activityBox=
                new VBox(

                        15,

                        new Label(
                                "Patients : "
                                +patients
                        ),

                        new Label(
                                "Doctors : "
                                +doctors
                        ),

                        new Label(
                                "Appointments : "
                                +appointments
                        ),

                        new Label(
                                "Revenue : ₹"
                                +revenue
                        )

                );

        activityBox.setPadding(
                new Insets(20)
        );

        activityBox.setStyle("""

                -fx-background-color:white;
                -fx-background-radius:15;
                -fx-border-radius:15;
                -fx-border-color:#C6D6E3;

        """);



        Button refresh=
                new Button(
                        "Refresh"
                );

        refresh.setStyle("""

                -fx-background-color:#5D8599;
                -fx-text-fill:white;
                -fx-font-weight:bold;

        """);

        refresh.setOnAction(e -> {

            view.getChildren().clear();

            view.getChildren().add(

                    new DashboardView()
                            .getView()

            );

        });



        view=
                new VBox(

                        35,

                        title,
                        cards,
                        recent,
                        activityBox,
                        refresh

                );

        view.setPadding(
                new Insets(30)
        );

    }



    private VBox createCard(

            String value,
            String text

    ){

        Label valueLabel=
                new Label(
                        value
                );

        valueLabel.setStyle("""

                -fx-font-size:34px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);


        Label textLabel=
                new Label(
                        text
                );


        VBox card=
                new VBox(

                        20,

                        valueLabel,
                        textLabel

                );

        card.setPadding(
                new Insets(30)
        );

        card.setPrefWidth(
                250
        );

        card.setStyle("""

                -fx-background-color:white;
                -fx-background-radius:15;
                -fx-border-radius:15;
                -fx-border-color:#C6D6E3;

        """);

        return card;

    }



    public Parent getView(){

        return view;

    }

}