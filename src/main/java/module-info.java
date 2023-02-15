module com.speetjens.p2patientendossier {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.speetjens.p2patientendossier to javafx.fxml;
    exports com.speetjens.p2patientendossier;
}