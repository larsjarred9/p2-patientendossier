package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.forms.PatientForm;
import com.speetjens.p2patientendossier.model.Patients;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class PatientsScreen {



    private final Scene patientsScene;

    public PatientsScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("patients"), getContent());

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
            Button add = new Button("Patient toevoegen");
            Button remove = new Button("Patient verwijderen");
            header.getChildren().addAll(title, text, add, remove);

            add.setOnAction(e -> {
                HelloApplication.mainStage.setScene(new PatientForm().getPatientForm());
            });


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

        remove.setOnAction(e -> {
            Patients selectedItem = table.getSelectionModel().getSelectedItem();

            if(selectedItem != null) {
                table.getItems().remove(selectedItem);

                // get id from item
                Integer id = selectedItem.getId();

                // remove from database
                new Patients().removePatient(id);
            }

            table.refresh();
        });



        content.getChildren().addAll(header, table);
        return content;
    }
}

