package com.speetjens.p2patientendossier.screens;

import com.speetjens.p2patientendossier.HelloApplication;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class LoginScreen {


    private final Scene loginScreen;

    public LoginScreen() {
        Pane container = new Pane();
        container.setId("container");

        container.getChildren().addAll(getContent());

        loginScreen = new Scene(container);
        loginScreen.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;700;900");
        loginScreen.getStylesheets().add(HelloApplication.class.getResource("stylesheets/style.css").toString());
    }

    /**
     * Get the login scene
     * @return Scene
     */
    public Scene getLoginScreen() {
        return loginScreen;
    }

    /**
     * Return the content of the login screen
     * @return Pane
     */
    public Pane getContent() {
        FlowPane content = new FlowPane();
        content.setId("content");

        content.getChildren().addAll();

        return content;
    }

}
