package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Users;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoginScreen {


    private final Scene loginScreen;

    public LoginScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getContent());

        loginScreen = new Scene(container);
        loginScreen.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        loginScreen.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the login scene
     * @return Scene
     */
    public Scene getLoginScreen() {
        return loginScreen;
    }

    /**
     * Return the content of the login screen
     * @return Pane
     */
    public Pane getContent() {
        FlowPane content = new FlowPane();
        content.setId("register");
        content.setPadding(new Insets(50, 25, 50, 25));
        content.relocate(365, 40);
        content.setStyle("-fx-background-color: white;");

        content.setAlignment(Pos.CENTER);

        Image logo = new Image(HelloApplication.class.getResource("images/logo.png").toString());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setFitHeight(25);

        Text title = new Text("Please enter your login credentials to continue");

        FlowPane form = new FlowPane();
        form.setOrientation(Orientation.VERTICAL);
        form.setVgap(20);
        form.setAlignment(Pos.CENTER);


        Label usernameLabel = new Label("Username");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setPrefWidth(300);

        Label passwordLabel = new Label("Password");

        PasswordField password = new PasswordField();
        password.setPromptText("********");
        password.setPrefWidth(300);


        Button submit = new Button("Login →");

        Text error = new Text("");
        error.setStyle("-fx-fill: red;");

        form.getChildren().addAll(usernameLabel, username, passwordLabel, password, submit, error);

        content.getChildren().addAll(logoView, title, form);

        submit.setOnAction(e -> {
            // validate form values (if not empty username and password)
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                error.setText("Please fill in all fields");
                return;
            }

            // valiate from model (if username and password are correct)

            Users user = new Users();
            if(!user.comparePassword(username.getText(), password.getText())) {
                error.setText("Username or password is incorrect");
                return;
            }

            // if valid, go to dashboard
            HelloApplication.mainStage.setScene(new DashboardScreen().getDashboardScene());
        });


        return content;
    }

}
