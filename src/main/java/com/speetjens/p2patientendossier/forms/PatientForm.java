package com.speetjens.p2patientendossier.forms;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Patients;
import com.speetjens.p2patientendossier.screens.PatientsScreen;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Date;

public class PatientForm {



    private final Scene patientForm;

    public PatientForm() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("patients"), getContent());

        patientForm = new Scene(container);
        patientForm.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        patientForm.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getPatientForm() {
        return patientForm;
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
        Text text = new Text("Vul de onderstaande gegevens in om een nieuwe patient toe te voegen.");
        Button button = new Button("Terug naar overzicht");

        button.setOnAction(e -> {
           HelloApplication.mainStage.setScene(new PatientsScreen().getPatientsScene());
        });

        header.getChildren().addAll(title, text, button);

        FlowPane form = new FlowPane();

        form.setVgap(10);
        form.setHgap(10);

        // firstName, lastName, email, phone, birthDate, sex, nationality, address, zip, city, country inputs

        TextField firstName = new TextField();
        firstName.setPromptText("Voornaam");

        TextField lastName = new TextField();
        lastName.setPromptText("Achternaam");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField phone = new TextField();
        phone.setPromptText("Telefoonnummer");

        DatePicker birthDate = new DatePicker();
        birthDate.setPromptText("Geboortedatum");


        ComboBox<String> sex = new ComboBox<>();
        sex.getItems().addAll("V", "M", "X");

        TextField nationality = new TextField();
        nationality.setPromptText("Nationaliteit");

        TextField address = new TextField();
        address.setPromptText("Adres");

        TextField zip = new TextField();
        zip.setPromptText("Postcode");

        TextField city = new TextField();
        city.setPromptText("Stad");

        TextField country = new TextField();
        country.setPromptText("Land");

        Button submit = new Button("Patient toevoegen");

        Text error = new Text("");
        error.setStyle("-fx-fill: red;");

        form.getChildren().addAll(firstName, lastName, email, phone, birthDate, sex, nationality, address, zip, city, country, submit, error);

        submit.setOnAction(e -> {

            // check if all inputs are set
            if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || sex.getValue().isEmpty() || nationality.getText().isEmpty() || address.getText().isEmpty() || zip.getText().isEmpty() || city.getText().isEmpty() || country.getText().isEmpty()) {
                error.setText("Vul alle velden in");
                return;
            }

            // check if birthdate is valid
            if(birthDate.getValue() == null) {
                error.setText("Vul een geldige geboortedatum in");
                return;
            }

            if(birthDate.getValue().isAfter(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate())) {
                error.setText("Vul een geldige geboortedatum in");
                return;
            }


            // check if email is valid
            if(!email.getText().contains("@")) {
                error.setText("Vul een geldig email adres in");
                return;
            }

            // check if phone number is valid international number
            if(!phone.getText().startsWith("+")) {
                error.setText("Vul een geldig telefoonnummer in");
                return;
            }

            // add patient to database
            Boolean status = new Patients().addPatients(new Patients(firstName.getText(), lastName.getText(), email.getText(), phone.getText(), birthDate.getValue().toString(), sex.getValue(), nationality.getText(), address.getText(), zip.getText(), city.getText(), country.getText()));

            if(status) {
                error.setText("Patient is toegevoegd");
                error.setStyle("-fx-fill: green;");
            } else {
                error.setText("Er is iets fout gegaan, probeer het later nog eens");
            }
        });

        content.getChildren().addAll(header, form);
        return content;
    }
}
