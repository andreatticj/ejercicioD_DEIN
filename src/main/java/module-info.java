module eu.andreatt.ejerciciob_dein {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.andreatt.ejerciciob_dein to javafx.fxml;
    exports eu.andreatt.ejerciciob_dein;
    exports eu.andreatt.ejerciciob_dein.controller;
    opens eu.andreatt.ejerciciob_dein.controller to javafx.fxml;
    exports eu.andreatt.ejerciciob_dein.application;
    opens eu.andreatt.ejerciciob_dein.application to javafx.fxml;
}