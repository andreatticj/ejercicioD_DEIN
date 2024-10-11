module eu.andreatt.ejercicioc_dein {
    requires javafx.controls;
    requires javafx.fxml;


//    opens eu.andreatt.ejercicioc_dein to javafx.fxml;
//    exports eu.andreatt.ejercicioc_dein;
    exports eu.andreatt.ejercicioc_dein.controller;
    opens eu.andreatt.ejercicioc_dein.controller to javafx.fxml;
    exports eu.andreatt.ejercicioc_dein.application;
    opens eu.andreatt.ejercicioc_dein.application to javafx.fxml;
}