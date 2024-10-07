module eu.andreatt.ejerciciob_dein {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.andreatt.ejerciciob_dein to javafx.fxml;
    exports eu.andreatt.ejerciciob_dein;
}