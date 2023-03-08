package com.speetjens.p2patientendossier.forms;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Medecine;
import com.speetjens.p2patientendossier.screens.MedicationScreen;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class MedicineForm {

    private final Scene medecineForm;

    public MedicineForm() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("medication"), getContent());

        medecineForm = new Scene(container);
        medecineForm.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        medecineForm.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getMedecineForm() {
        return medecineForm;
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
        Text text = new Text("Vul de onderstaande gegevens in om een nieuwe medicijn toe te voegen.");
        Button button = new Button("Terug naar overzicht");

        button.setOnAction(e -> {
           HelloApplication.mainStage.setScene(new MedicationScreen().getMedicationScene());
        });

        header.getChildren().addAll(title, text, button);

        FlowPane form = new FlowPane();

        form.setVgap(10);
        form.setHgap(10);

        // form fields

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField treats = new TextField();
        treats.setPromptText("Treats");

        TextField categories = new TextField();
        categories.setPromptText("Categories");

        TextField storage = new TextField();
        storage.setPromptText("Storage");

        TextField dosage = new TextField();
        dosage.setPromptText("Dosage");

        Button submit = new Button("Medicijn toevoegen");

        Text error = new Text("");
        error.setStyle("-fx-fill: red;");

        form.getChildren().addAll(name, treats, categories, storage, dosage, submit, error);

        submit.setOnAction(e -> {

            // check if all inputs are set
            if(name.getText().isEmpty() || treats.getText().isEmpty() || categories.getText().isEmpty() || storage.getText().isEmpty() || dosage.getText().isEmpty()) {
                error.setText("Vul alle velden in");
                return;
            }

            // add medicine to database
            Boolean status = new Medecine().addMedicine(new Medecine(name.getText(), treats.getText(), categories.getText(), storage.getText(), dosage.getText()));

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
