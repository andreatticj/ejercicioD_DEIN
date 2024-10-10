package eu.andreatt.ejerciciob_dein.controller;

import eu.andreatt.ejerciciob_dein.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Window;

public class HelloController {

    @FXML
    private TableView<Persona> tabla;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colApellido;

    @FXML
    private TableColumn<Persona, Integer> colEdad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colEdad.setCellValueFactory(cellData -> cellData.getValue().edadProperty().asObject());

        // Añadir los datos a la tabla
        tabla.setItems(personas);
    }

    @FXML
    void agregarPersona(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow();

        // Verificar la validez de los datos
        String errores = verificarInfo();
        if (!errores.isEmpty()) {
            mostrarAlertError(win, errores);
            return; // No continuar si hay errores
        }

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        int edad = Integer.parseInt(txtEdad.getText());

        Persona nuevaPersona = new Persona(nombre, apellido, edad);

        // Comprobar si la persona ya existe
        if (personas.contains(nuevaPersona)) {
            mostrarAlertError(win, "Esa persona ya existe");
            limpiarCampos();
        } else {
            // Agregar la nueva persona a la lista
            personas.add(nuevaPersona);
            // Limpiar los campos después de agregar
            limpiarCampos();
            mostrarAlertInfo(win,"Persona añadida correctamente");
        }
    }

    private void mostrarAlertError(Window win, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(win);
        alert.setHeaderText(null);
        alert.setTitle("TUS DATOS");
        alert.setContentText(error); // Muestra el mensaje de error pasado como argumento
        alert.showAndWait();
    }

    /**
     * Verifica si los datos proporcionados en el formulario son válidos.
     *
     * @return Una cadena de texto con los errores encontrados o una cadena vacía si no hay errores.
     */
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

    private void mostrarAlertInfo(Window win, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(win);
        alert.setHeaderText(null); // Sin encabezado
        alert.setTitle("Información"); // Título del Alert
        alert.setContentText(mensaje); // Mensaje de una sola línea
        alert.showAndWait(); // Mostrar el Alert y esperar a que se cierre
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
    }
}
