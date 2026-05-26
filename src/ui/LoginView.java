package ui;

import dao.LoginDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.User;

public class LoginView {

    private Parent view;

    public LoginView(){

        LoginDAO dao =
                new LoginDAO();


        Label icon =
                new Label(
                        "🏥"
                );

        icon.setStyle(
                "-fx-font-size:50px;"
        );



        Label title =
                new Label(
                        "Hospital Management"
                );

        title.setStyle("""

                -fx-font-size:28px;
                -fx-font-weight:bold;
                -fx-text-fill:#324760;

        """);



        Label subtitle =
                new Label(
                        "Login to continue"
                );

        subtitle.setStyle(
                "-fx-text-fill:gray;"
        );



        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Username"
        );

        usernameField.setMaxWidth(
                350
        );



        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Password"
        );

        passwordField.setMaxWidth(
                350
        );



        Label message =
                new Label();



        Button loginButton =
                new Button(
                        "Login"
                );

        loginButton.setPrefWidth(
                350
        );

        loginButton.setStyle("""

                -fx-background-color:#5f89a1;
                -fx-text-fill:white;
                -fx-font-size:16px;
                -fx-font-weight:bold;
                -fx-background-radius:12;

        """);



        loginButton.setOnAction(e -> {

            User user =
                    dao.login(

                            usernameField.getText(),

                            passwordField.getText()

                    );


            if(user!=null){

                MainDashboard.openDashboard(

                        user.getRole()

                );

            }

            else{

                message.setText(
                        "Invalid Credentials"
                );

                message.setStyle(
                        "-fx-text-fill:red;"
                );

            }

        });



        VBox card =
                new VBox(

                        18,

                        icon,
                        title,
                        subtitle,
                        usernameField,
                        passwordField,
                        loginButton,
                        message

                );



        card.setAlignment(
                Pos.CENTER
        );



        card.setPadding(
                new Insets(
                        50
                )
        );



        card.setMaxWidth(
                500
        );



        card.setStyle("""

                -fx-background-color:white;
                -fx-background-radius:20;
                -fx-border-radius:20;
                -fx-border-color:#D9D9D9;

        """);



        BorderPane root =
                new BorderPane();

        root.setCenter(
                card
        );



        root.setStyle("""

                -fx-background-color:#F4F6F8;

        """);



        view =
                root;

    }



    public Parent getView(){

        return view;

    }

}