package com.speetjens.p2patientendossier;

import com.speetjens.p2patientendossier.screens.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static Stage mainStage;
    public static int[] applicationSize = {1200, 650};

    @Override
    public void start(Stage stage) {

        mainStage = stage;
        Image icon = new Image(getClass().getResourceAsStream("/com/speetjens/p2patientendossier/images/icon.png"));
        mainStage.getIcons().add(icon);
        mainStage.setWidth(applicationSize[0]);
        mainStage.setHeight(applicationSize[1]);
        mainStage.setResizable(false);
        mainStage.setTitle("AZ - Patientdossier");

        mainStage.setScene(new LoginScreen().getLoginScreen());
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Pane getHeader(String bold) {
        FlowPane header = new FlowPane();
        header.setId("header");
        header.setPadding(new Insets(20, 20, 20, 20));
        header.setStyle("-fx-background-color: white;");
        header.setPrefWidth(HelloApplication.applicationSize[0]);

        // logo in header
        Image logo = new Image(HelloApplication.class.getResource("images/logo.png").toString());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setFitHeight(25);

        // navigation bar horizontal java fx
        FlowPane navigation = new FlowPane();
        navigation.setId("navigation");
        navigation.setPadding(new Insets(5, 5, 5, 5));
        navigation.setStyle("-fx-background-color: white;");

        navigation.setPrefWidth(HelloApplication.applicationSize[0]);

        // navigation bar items
        Text home = new Text("Dashboard");
        Text person = new Text("Patienten");
        Text medication = new Text("Medicaties");
        Text allergies = new Text("Alergieën");
        Text logout = new Text("Uitloggen");

        // add margin to navigation items
        FlowPane.setMargin(logoView, new Insets(0, 50, 0, 0));
        FlowPane.setMargin(home, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(person, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(medication, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(allergies, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(logout, new Insets(0, 20, 0, 0));

        // make tab bold when string bold is equal to tab name
        if (bold.equals("home")) {
            home.setStyle("-fx-font-weight: bold;");
        } else if (bold.equals("patients")) {
            person.setStyle("-fx-font-weight: bold;");
        } else if (bold.equals("medication")) {
            medication.setStyle("-fx-font-weight: bold;");
        } else if (bold.equals("allergies")) {
            allergies.setStyle("-fx-font-weight: bold;");
        }

        // when click on dasboard text
        home.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new DashboardScreen().getDashboardScene());
        });

        // when click on text patients
        person.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new PatientsScreen().getPatientsScene());
        });

        // WHen click on text medication
        medication.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new MedicationScreen().getMedicationScene());
        });

        // WHen click on text allergies
        allergies.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new AllergiesScreen().getAllergiesScene());
        });

        // when click on logout text
        logout.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new LoginScreen().getLoginScreen());
        });


        // add navigation items to navigation bar
        navigation.getChildren().addAll(logoView, home, person, medication, allergies, logout);

        header.getChildren().addAll(navigation);

        return header;
    }
}