package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Medecine;
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

public class MedicationScreen {



    private final Scene medicationScene;

    public MedicationScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getHeader(), getContent());

        medicationScene = new Scene(container);
        medicationScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        medicationScene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getMedicationScene() {
        return medicationScene;
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

        Text title = new Text("Medicatie");
        title.setId("title");
        Text text = new Text("Selecteer een medicijn om de gegevens in te zien.");
        Button button = new Button("Nieuwe medicijnen toevoegen");
        header.getChildren().addAll(title, text, button);


        // table

        TableView<Medecine> table = new TableView();
        table.setPadding(new Insets(10, 0, 0, 0));


        TableColumn<Medecine, String> name =
                new TableColumn<>("Naam");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Medecine, String> treats =
                new TableColumn<>("Verhelpt");

        treats.setCellValueFactory(
                new PropertyValueFactory<>("treats"));


        TableColumn<Medecine, String> categories =
                new TableColumn<>("Waarschuwingen");

        categories.setCellValueFactory(
                new PropertyValueFactory<>("categories"));

        TableColumn<Medecine, String> storage =
                new TableColumn<>("Opslag");

        storage.setCellValueFactory(
                new PropertyValueFactory<>("storage"));

        TableColumn<Medecine, String> dosage =
                new TableColumn<>("Dosering");

        dosage.setCellValueFactory(
                new PropertyValueFactory<>("dosage"));

        // give colums a width
        name.setPrefWidth(175);
        treats.setPrefWidth(175);
        categories.setPrefWidth(200);
        storage.setPrefWidth(175);
        dosage.setPrefWidth(175);



        table.getColumns().addAll(name, treats, categories, storage, dosage);

        ArrayList<Object> medecinesList = new Medecine().getMedecines();

        medecinesList.forEach(e -> {
            if(e instanceof Medecine) {
                System.out.println(e);
                table.getItems().add((Medecine) e);
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
        medication.setStyle("-fx-font-weight: bold;");

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

