module com.speetjens.p2patientendossier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.speetjens.p2patientendossier to javafx.fxml;
    exports com.speetjens.p2patientendossier;

    opens com.speetjens.p2patientendossier.model to javafx.base;
}