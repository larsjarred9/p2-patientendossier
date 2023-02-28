package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DashboardScreen {



    private final Scene dashboardScene;

    public DashboardScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getHeader(), getContent());

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

            Text title = new Text("Welkom "+"gebruiker"+",");
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
            Text stat1Value = new Text("10");
            stat1Value.setId("number");

            stat1.getChildren().addAll(stat1Title, stat1Value);

            FlowPane stat2 = new FlowPane();

            stat2.setOrientation(Orientation.VERTICAL);

            Text stat2Title = new Text("Aantal medicijnen");

            Text stat2Value = new Text("10");
            stat2Value.setId("number");

            stat2.getChildren().addAll(stat2Title, stat2Value);

            stats.getChildren().addAll(stat1, stat2);



        content.getChildren().addAll(items, stats);
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
        Text logout = new Text("Uitloggen");

        // add margin to navigation items
        FlowPane.setMargin(logoView, new Insets(0, 50, 0, 0));
        FlowPane.setMargin(home, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(person, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(medication, new Insets(0, 20, 0, 0));
        FlowPane.setMargin(logout, new Insets(0, 20, 0, 0));

        // Make current page text bold
        home.setStyle("-fx-font-weight: bold;");

        // when click on dasboard text
        home.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new DashboardScreen().getDashboardScene());
        });

        // when click on text gas
        person.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new PersonsScreen().getPersonsScene());
        });

        // WHen click on stroom text
        medication.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new LoginScreen().getLoginScreen());
        });


        // when click on logout text
        logout.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new LoginScreen().getLoginScreen());
        });


        // add navigation items to navigation bar
        navigation.getChildren().addAll(logoView, home, person, medication, logout);

        header.getChildren().addAll(navigation);

        return header;
    }
}

