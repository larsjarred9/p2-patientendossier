package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.forms.AllergyForm;
import com.speetjens.p2patientendossier.model.Allergies;
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

public class AllergiesScreen {



    private final Scene allergiesScene;

    public AllergiesScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("allergies"), getContent());

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

        button.setOnAction(e -> {
            HelloApplication.mainStage.setScene(new AllergyForm().getAllergyForm());
        });


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
}

