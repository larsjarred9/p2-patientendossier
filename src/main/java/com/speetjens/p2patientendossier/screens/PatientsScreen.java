package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Patients;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class PatientsScreen {



    private final Scene patientsScene;

    public PatientsScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getHeader(), getContent());

        patientsScene = new Scene(container);
        patientsScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        patientsScene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getPatientsScene() {
        return patientsScene;
    }

    public Pane getContent() {
        FlowPane content = new FlowPane();
        content.setId("dashboard");
        content.setPadding(new Insets(50, 25, 50, 25));

        content.setVgap(10);
        content.relocate(85, 100);
        content.setStyle("-fx-background-color: white;");

            FlowPane header = new FlowPane();
            header.setOrientation(Orientation.HORIZONTAL);
            header.setVgap(10);

            Text title = new Text("Patienten");
            title.setId("title");
            Text text = new Text("Selecteer een persoon om de gegevens in te zien.");
            Button button = new Button("Nieuwe Patient toevoegen");
            header.getChildren().addAll(title, text, button);


            // table

            TableView<Patients> table = new TableView();
            table.setPadding(new Insets(10, 0, 0, 0));


            TableColumn<Patients, String> firstName =
                    new TableColumn<>("Voornaam");

            firstName.setCellValueFactory(
                    new PropertyValueFactory<>("firstName"));

            TableColumn<Patients, String> lastName =
                    new TableColumn<>("Achternaam");

            lastName.setCellValueFactory(
                    new PropertyValueFactory<>("lastName"));


            TableColumn<Patients, String> birthDate =
                    new TableColumn<>("Geboortedatum");

            birthDate.setCellValueFactory(
                    new PropertyValueFactory<>("birthDate"));

            TableColumn<Patients, String> gender =
                    new TableColumn<>("Geslacht");

            gender.setCellValueFactory(
                    new PropertyValueFactory<>("sex"));

            TableColumn<Patients, String> nationality =
                    new TableColumn<>("Nationaliteit");

            nationality.setCellValueFactory(
                    new PropertyValueFactory<>("nationality"));

            TableColumn<Patients, String> email =
                    new TableColumn<>("E-mail");

            email.setCellValueFactory(
                    new PropertyValueFactory<>("email"));

            TableColumn<Patients, String> phone =
                    new TableColumn<>("Telefoonnummer");

            phone.setCellValueFactory(
                    new PropertyValueFactory<>("phone"));

            // give colums a width
            firstName.setPrefWidth(100);
            lastName.setPrefWidth(150);
            birthDate.setPrefWidth(100);
            gender.setPrefWidth(100);
            nationality.setPrefWidth(100);
            email.setPrefWidth(175);
            phone.setPrefWidth(175);



            table.getColumns().addAll(firstName, lastName, birthDate, gender, nationality, email, phone);

            ArrayList<Object> patientList = new Patients().getPatients();

            patientList.forEach(e -> {
                if(e instanceof Patients) {
                    table.getItems().add((Patients) e);
                }
            });

            table.setPrefHeight(325);



        content.getChildren().addAll(header, table);
        return content;
    }

    public Pane getHeader() {
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
        Text allergies = new Text("Allergieën");
        Text logout = new Text("Uitloggen");

        // add margin to navigation items
        FlowPane.setMargin(logoView, new Insets(0, 50, 0, 0));
        FlowPane.setMargin(home, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(person, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(medication, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(allergies, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(logout, new Insets(0, 20, 0, 0));

        // Make current page text bold
        person.setStyle("-fx-font-weight: bold;");

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

        // WHen click on text medication
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

