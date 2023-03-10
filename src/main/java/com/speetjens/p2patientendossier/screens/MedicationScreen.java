package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.forms.MedicineForm;
import com.speetjens.p2patientendossier.model.Allergies;
import com.speetjens.p2patientendossier.model.Medecine;
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

public class MedicationScreen {



    private final Scene medicationScene;

    public MedicationScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("medication"), getContent());

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
        Button add = new Button("Medicijn toevoegen");
        Button remove = new Button("Medicijn verwijderen");
        header.getChildren().addAll(title, text, add, remove);

        add.setOnAction(e -> {
            HelloApplication.mainStage.setScene(new MedicineForm().getMedecineForm());
        });


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

        remove.setOnAction(e -> {
            Medecine selectedItem = table.getSelectionModel().getSelectedItem();

            if(selectedItem != null) {
                table.getItems().remove(selectedItem);

                // get id from item
                Integer id = selectedItem.getId();

                // remove from database
                new Medecine().removeMedicine(id);
            }

            table.refresh();
        });



        content.getChildren().addAll(header, table);
        return content;
    }
}

