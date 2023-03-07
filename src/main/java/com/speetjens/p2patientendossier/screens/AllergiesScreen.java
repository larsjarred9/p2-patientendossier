package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Allergies;
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

public class AllergiesScreen {



    private final Scene allergiesScene;

    public AllergiesScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getHeader(), getContent());

        allergiesScene = new Scene(container);
        allergiesScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        allergiesScene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getAllergiesScene() {
        return allergiesScene;
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

        Text title = new Text("Alergieën");
        title.setId("title");
        Text text = new Text("Selecteer een alergie om de gegevens in te zien.");
        Button button = new Button("Nieuwe medicijnen toevoegen");
        header.getChildren().addAll(title, text, button);


        // table
        TableView<Allergies> table = new TableView();
        table.setPadding(new Insets(10, 0, 0, 0));


        TableColumn<Allergies, String> name =
                new TableColumn<>("Naam");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));


        // give colums a width
        name.setPrefWidth(100);

        table.getColumns().add(name);

        ArrayList<Object> patientList = new Allergies().getAllergies();

        patientList.forEach(e -> {
            if(e instanceof Allergies) {
                table.getItems().add((Allergies) e);
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
        Text allergies = new Text("Alergieën");
        Text logout = new Text("Uitloggen");

        // add margin to navigation items
        FlowPane.setMargin(logoView, new Insets(0, 50, 0, 0));
        FlowPane.setMargin(home, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(person, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(medication, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(allergies, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(logout, new Insets(0, 20, 0, 0));

        // Make current page text bold
        allergies.setStyle("-fx-font-weight: bold;");

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

