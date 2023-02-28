package com.speetjens.p2patientendossier;

import com.speetjens.p2patientendossier.screens.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static Stage mainStage;
    public static int[] applicationSize = {1200, 650};

    @Override
    public void start(Stage stage) {

        mainStage = stage;
        mainStage.setWidth(applicationSize[0]);
        mainStage.setHeight(applicationSize[1]);
        mainStage.setResizable(false);
        mainStage.setTitle("ADRZ - Patientdossier");

        mainStage.setScene(new LoginScreen().getLoginScreen());
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}