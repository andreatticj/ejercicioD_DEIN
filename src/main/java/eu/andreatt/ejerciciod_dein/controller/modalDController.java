package eu.andreatt.ejerciciod_dein.controller;

import eu.andreatt.ejerciciod_dein.model.Persona;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private Persona persona;
    private ObservableList<Persona> personas; // Cambiar a sin inicializar

    public void setPersonas(ObservableList<Persona> personas) {
        this.personas = personas;
    }

    @FXML
    void cancelar(ActionEvent event) {
        // Cerrar solo la ventana actual
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
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
                limpiarCampos();
               // mostrarAlertInfo(win, "Persona añadida correctamente");
            }
        }
    }

    // Método para devolver la persona
    public Persona getPersona() {
        return persona;
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
    private void limpiarCampos() {
        txtNombre.clear(); // Limpia el campo de nombre
        txtApellido.clear(); // Limpia el campo de apellido
        txtEdad.clear(); // Limpia el campo de edad
    }
}
