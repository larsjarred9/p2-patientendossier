module com.speetjens.p2patientendossier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.speetjens.p2patientendossier to javafx.fxml;
    exports com.speetjens.p2patientendossier;
    exports com.speetjens.p2patientendossier.controller;
    opens com.speetjens.p2patientendossier.controller to javafx.fxml;
}