package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import com.speetjens.p2patientendossier.model.Allergies;
import com.speetjens.p2patientendossier.model.Medecine;
import com.speetjens.p2patientendossier.model.Patients;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DashboardScreen {



    private final Scene dashboardScene;

    public DashboardScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(new HelloApplication().getHeader("home"), getContent());

        dashboardScene = new Scene(container);
        dashboardScene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        dashboardScene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the dashboard scene
     * @return Scene
     */
    public Scene getDashboardScene() {
        return dashboardScene;
    }

    public Pane getContent() {
        FlowPane content = new FlowPane();
        content.setId("dashboard");
        content.setPadding(new Insets(50, 25, 50, 25));
        content.relocate(85, 100);
        content.setStyle("-fx-background-color: white;");

            FlowPane items = new FlowPane();

            String username = "gebruiker";

            Text title = new Text("Welkom "+username+",");
            title.setId("title");

            Label text = new Label("Met de navigatie knoppen kunt u medicijnen beheren en deze medicatie toevoegen aan patienten. Onderstaand zijn enkle statistieken weergegeven.");
            text.setWrapText(true);
            items.getChildren().addAll(title, text);

            FlowPane stats = new FlowPane();
            stats.setHgap(10);
            stats.setPadding(new Insets(50, 0, 50, 0));

            FlowPane stat1 = new FlowPane();
            stat1.setOrientation(Orientation.VERTICAL);
            Text stat1Title = new Text("Aantal patienten");
            Patients patients = new Patients();
            Text stat1Value = new Text(String.valueOf(patients.getPatients().size()));
            stat1Value.setId("number");

            stat1.getChildren().addAll(stat1Title, stat1Value);

            FlowPane stat2 = new FlowPane();

            stat2.setOrientation(Orientation.VERTICAL);

            Text stat2Title = new Text("Aantal medicijnen");

            Medecine medecine = new Medecine();
            Text stat2Value = new Text(String.valueOf(medecine.getMedecines().size()));
            stat2Value.setId("number");


            stat2.getChildren().addAll(stat2Title, stat2Value);

            FlowPane stat3 = new FlowPane();

            stat3.setOrientation(Orientation.VERTICAL);

            Text stat3Title = new Text("Aantal alergien");

            Allergies alergies = new Allergies();
            Text stat3Value = new Text(String.valueOf(alergies.getAllergies().size()));
            stat3Value.setId("number");


            stat3.getChildren().addAll(stat3Title, stat3Value);

            stats.getChildren().addAll(stat1, stat2, stat3);



        content.getChildren().addAll(items, stats);
        return content;
    }
}

