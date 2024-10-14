package eu.andreatt.ejerciciod_dein.controller;

import eu.andreatt.ejerciciod_dein.model.Persona;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class modalDController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    private ObservableList<Persona> personas = FXCollections.observableArrayList();


    @FXML
    void cancelar(ActionEvent event) {
        Platform.exit(); // Cierra la aplicación
    }

    @FXML
    void guardar(ActionEvent event) {
        String errores = verificarInfo();
        if (errores.isEmpty()){
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Persona nuevaPersona = new Persona(nombre, apellido, edad);

            // Comprobar si la persona ya existe
            if (personas.contains(nuevaPersona)) {
               // mostrarAlertError(win, "Esa persona ya existe");
                //limpiarCampos();
            } else {
                // Agregar la nueva persona a la lista
                personas.add(nuevaPersona);
                // Limpiar los campos después de agregar
               // limpiarCampos();
               // mostrarAlertInfo(win, "Persona añadida correctamente");
            }
        }
    }


    private String verificarInfo() {
        StringBuilder errores = new StringBuilder();

        // Verificar que el campo nombre no esté vacío
        if (txtNombre.getText().isEmpty()) {
            errores.append("El campo Nombre es obligatorio.\n");
        }

        // Verificar que el campo apellido no esté vacío
        if (txtApellido.getText().isEmpty()) {
            errores.append("El campo Apellido es obligatorio.\n");
        }

        // Verificar que el campo edad no esté vacío y sea un número válido
        if (txtEdad.getText().isEmpty()) {
            errores.append("El campo Edad es obligatorio.\n");
        } else {
            try {
                Integer.parseInt(txtEdad.getText()); // Intentar convertir a número
            } catch (NumberFormatException e) {
                errores.append("El campo Edad debe ser un número válido.\n");
            }
        }

        return errores.toString();
    }
}