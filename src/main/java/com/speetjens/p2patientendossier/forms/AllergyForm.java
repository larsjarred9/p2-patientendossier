package com.speetjens.p2patientendossier.forms;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Allergies;
import com.speetjens.p2patientendossier.screens.AllergiesScreen;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AllergyForm {

    private final Scene allergyForm;

    public AllergyForm() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("allergies"), getContent());

        allergyForm = new Scene(container);
        allergyForm.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        allergyForm.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getAllergyForm() {
        return allergyForm;
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

        Text title = new Text("Allergie");
        title.setId("title");
        Text text = new Text("Vul de onderstaande gegevens in om een nieuwe alergie / dieetwens toe te voegen.");
        Button button = new Button("Terug naar overzicht");

        button.setOnAction(e -> {
           HelloApplication.mainStage.setScene(new AllergiesScreen().getAllergiesScene());
        });

        header.getChildren().addAll(title, text, button);

        FlowPane form = new FlowPane();

        form.setVgap(10);
        form.setHgap(10);

        // form fields

        TextField name = new TextField();
        name.setPromptText("Name");

        Button submit = new Button("Dieetwens toevoegen");

        Text error = new Text("");
        error.setStyle("-fx-fill: red;");

        form.getChildren().addAll(name, submit, error);

        submit.setOnAction(e -> {

            // check if all inputs are set
            if(name.getText().isEmpty()) {
                error.setText("Vul alle velden in");
                return;
            }

            // add medicine to database
            Boolean status = new Allergies().addAllergy(new Allergies(name.getText()));

            if(status) {
                error.setText("Het Medicijn is toegevoegd");
                error.setStyle("-fx-fill: green;");
            } else {
                error.setText("Er is iets fout gegaan, probeer het later nog eens");
            }
        });

        content.getChildren().addAll(header, form);
        return content;
    }
}
