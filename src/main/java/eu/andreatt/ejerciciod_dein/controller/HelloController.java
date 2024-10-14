package eu.andreatt.ejerciciod_dein.controller;

import eu.andreatt.ejerciciod_dein.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

import java.util.Optional;

public class HelloController {

    @FXML
    private Button btnAgregarPersona;

    @FXML
    private TableView<Persona> tabla;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colApellido;

    @FXML
    private TableColumn<Persona, Integer> colEdad;


    @FXML
    void agregarPersona(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow();
        mostrarInputDialog(win);
    }

    private void mostrarInputDialog(Window win) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DATOS");
        dialog.setHeaderText(null);
        dialog.initOwner(win);
        dialog.setContentText("Introduce tu nombre:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Tu nombre: " + result.get());
        }
    }

}
