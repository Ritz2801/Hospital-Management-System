package ui;

import dao.DashboardDAO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ReportsView {

    private VBox view;

    DashboardDAO dao =
            new DashboardDAO();

    public ReportsView(){

        String data =
                dao.getDashboardData();

        String patients="0";
        String doctors="0";
        String appointments="0";
        String revenue="0";

        try{

            data = data.replace("{","")
                    .replace("}","");

            String[] fields =
                    data.split(",");

            for(String field : fields){

                String[] pair =
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



        // TITLE

        Label title =
                new Label(
                        "Reports & Analytics"
                );

        title.setStyle("""
                -fx-font-size:32px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;
        """);



        // CARDS

        HBox cards =
                new HBox(
                        25,

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



        // BAR CHART

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        BarChart<String,Number> barChart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );

        barChart.setTitle(
                "Hospital Statistics"
        );

        barChart.setLegendVisible(
                false
        );

        barChart.setPrefSize(
                550,
                350
        );


        XYChart.Series<String,Number> series =
                new XYChart.Series<>();


        series.getData().add(
                new XYChart.Data<>(
                        "Patients",
                        Integer.parseInt(
                                patients
                        )
                )
        );

        series.getData().add(
                new XYChart.Data<>(
                        "Doctors",
                        Integer.parseInt(
                                doctors
                        )
                )
        );

        series.getData().add(
                new XYChart.Data<>(
                        "Appointments",
                        Integer.parseInt(
                                appointments
                        )
                )
        );


        barChart.getData().add(
                series
        );



        // PIE CHART

        PieChart pieChart =
                new PieChart(

                        FXCollections.observableArrayList(

                                new PieChart.Data(
                                        "Patients",
                                        Integer.parseInt(
                                                patients
                                        )
                                ),

                                new PieChart.Data(
                                        "Doctors",
                                        Integer.parseInt(
                                                doctors
                                        )
                                ),

                                new PieChart.Data(
                                        "Appointments",
                                        Integer.parseInt(
                                                appointments
                                        )
                                )

                        )

                );

        pieChart.setTitle(
                "Distribution"
        );

        pieChart.setPrefSize(
                500,
                350
        );



        HBox charts =
                new HBox(
                        40,
                        barChart,
                        pieChart
                );



        // REFRESH BUTTON

        Button refresh =
                new Button(
                        "Refresh"
                );

        refresh.setStyle("""

                -fx-background-color:#5f89a1;
                -fx-text-fill:white;
                -fx-font-weight:bold;
                -fx-background-radius:8;

        """);

        refresh.setOnAction(e -> {

            view.getChildren().setAll(

                    new ReportsView()
                            .getView()

            );

        });



        // MAIN VIEW

        view =
                new VBox(

                        30,

                        title,
                        cards,
                        charts,
                        refresh

                );

        view.setPadding(
                new Insets(
                        20
                )
        );

    }



    private VBox createCard(

            String value,
            String title

    ){

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setStyle("""

                -fx-font-size:30px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);


        Label titleLabel =
                new Label(
                        title
                );



        VBox card =
                new VBox(

                        20,

                        valueLabel,
                        titleLabel

                );

        card.setPadding(
                new Insets(
                        30
                )
        );

        card.setPrefWidth(
                270
        );

        card.setStyle("""

                -fx-background-color:white;
                -fx-border-color:#d6d6d6;
                -fx-border-radius:15;
                -fx-background-radius:15;

        """);

        return card;

    }



    public Parent getView(){

        return view;

    }

}