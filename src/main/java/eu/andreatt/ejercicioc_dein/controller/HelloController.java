package eu.andreatt.ejercicioc_dein.controller;

import eu.andreatt.ejercicioc_dein.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 * Controlador para gestionar la interfaz gráfica y la lista de personas.
 * Este controlador maneja la lógica para agregar, modificar y eliminar personas de una tabla.
 */
public class HelloController {

    @FXML
    private Button btnAgregarPersona, btnEliminar, btnModificar; // Botones para las acciones de la tabla
    @FXML
    private TableView<Persona> tabla; // Tabla para mostrar la lista de personas
    @FXML
    private TableColumn<Persona, String> colNombre, colApellido; // Columnas para nombre y apellido
    @FXML
    private TableColumn<Persona, Integer> colEdad; // Columna para edad
    @FXML
    private TextField txtNombre, txtApellido, txtEdad; // Campos de texto para entrada de datos

    private ObservableList<Persona> personas = FXCollections.observableArrayList(); // Lista observable de personas

    /**
     * Inicializa el controlador después de que los elementos FXML hayan sido cargados.
     * Configura las columnas de la tabla y la selección de la tabla.
     */
    @FXML
    public void initialize() {
        configurarTabla(); // Configura la tabla de personas
        configurarSeleccionTabla(); // Configura el evento de selección de la tabla
    }

    /**
     * Configura las columnas de la tabla para mostrar los atributos de {@code Persona}.
     */
    private void configurarTabla() {
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty()); // Muestra el nombre
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty()); // Muestra el apellido
        colEdad.setCellValueFactory(cellData -> cellData.getValue().edadProperty().asObject()); // Muestra la edad
        tabla.setItems(personas); // Establece la lista de personas en la tabla
    }

    /**
     * Configura el evento de selección de la tabla para mostrar los datos de la persona seleccionada en los campos de texto.
     */
    private void configurarSeleccionTabla() {
        tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarDatosEnCampos(newValue); // Muestra los datos de la persona seleccionada en los campos
            }
        });
    }

    /**
     * Muestra los datos de la persona en los campos de texto.
     *
     * @param persona La persona cuyos datos se van a mostrar.
     */
    private void mostrarDatosEnCampos(Persona persona) {
        txtNombre.setText(persona.getNombre()); // Establece el nombre en el campo de texto
        txtApellido.setText(persona.getApellido()); // Establece el apellido en el campo de texto
        txtEdad.setText(String.valueOf(persona.getEdad())); // Establece la edad en el campo de texto
    }

    /**
     * Maneja el evento de agregar una nueva persona a la lista.
     * Verifica la validez de los datos ingresados antes de agregar.
     *
     * @param event El evento que desencadena la acción.
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow(); // Obtiene la ventana actual
        String errores = verificarInfo(); // Verifica si hay errores en la información ingresada

        if (!errores.isEmpty()) {
            mostrarAlertError(win, errores); // Muestra errores si los hay
            return;
        }

        Persona nuevaPersona = crearPersonaDesdeCampos(); // Crea una nueva persona desde los campos
        if (personas.contains(nuevaPersona)) {
            mostrarAlertError(win, "Esa persona ya existe"); // Muestra error si la persona ya existe
            limpiarCampos(); // Limpia los campos
        } else {
            personas.add(nuevaPersona); // Agrega la nueva persona a la lista
            limpiarCampos(); // Limpia los campos después de agregar
            mostrarAlertInfo(win, "Persona añadida correctamente"); // Muestra mensaje de éxito
        }
    }

    /**
     * Crea un nuevo objeto {@code Persona} a partir de los datos ingresados en los campos de texto.
     *
     * @return Un nuevo objeto {@code Persona} con los datos ingresados.
     */
    private Persona crearPersonaDesdeCampos() {
        String nombre = txtNombre.getText(); // Obtiene el nombre
        String apellido = txtApellido.getText(); // Obtiene el apellido
        int edad = Integer.parseInt(txtEdad.getText()); // Obtiene la edad
        return new Persona(nombre, apellido, edad); // Devuelve un nuevo objeto Persona
    }

    /**
     * Verifica la validez de la información ingresada en los campos de texto.
     *
     * @return Una cadena con los errores encontrados, o una cadena vacía si no se encuentran errores.
     */
    private String verificarInfo() {
        StringBuilder errores = new StringBuilder(); // Acumula errores

        // Verifica que el campo nombre no esté vacío
        if (txtNombre.getText().isEmpty()) {
            errores.append("El campo Nombre es obligatorio.\n");
        }
        // Verifica que el campo apellido no esté vacío
        if (txtApellido.getText().isEmpty()) {
            errores.append("El campo Apellido es obligatorio.\n");
        }
        // Verifica que el campo edad no esté vacío
        if (txtEdad.getText().isEmpty()) {
            errores.append("El campo Edad es obligatorio.\n");
        } else {
            verificarEdad(errores); // Verifica si la edad es válida
        }

        return errores.toString(); // Devuelve los errores acumulados
    }

    /**
     * Verifica que el campo de edad contenga un número válido.
     *
     * @param errores Un {@code StringBuilder} para acumular errores.
     */
    private void verificarEdad(StringBuilder errores) {
        try {
            Integer.parseInt(txtEdad.getText()); // Intenta convertir a número
        } catch (NumberFormatException e) {
            errores.append("El campo Edad debe ser un número válido.\n"); // Acumula error si la conversión falla
        }
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    private void limpiarCampos() {
        txtNombre.clear(); // Limpia el campo de nombre
        txtApellido.clear(); // Limpia el campo de apellido
        txtEdad.clear(); // Limpia el campo de edad
    }

    /**
     * Muestra una alerta de error con un mensaje específico.
     *
     * @param win   La ventana sobre la que se mostrará la alerta.
     * @param error El mensaje de error a mostrar.
     */
    private void mostrarAlertError(Window win, String error) {
        mostrarAlert(win, Alert.AlertType.ERROR, "ERROR", error); // Llama al método general para mostrar alerta de error
    }

    /**
     * Muestra una alerta de información con un mensaje específico.
     *
     * @param win     La ventana sobre la que se mostrará la alerta.
     * @param mensaje El mensaje informativo a mostrar.
     */
    private void mostrarAlertInfo(Window win, String mensaje) {
        mostrarAlert(win, Alert.AlertType.INFORMATION, "INFO", mensaje); // Llama al método general para mostrar alerta informativa
    }

    /**
     * Muestra una alerta de acuerdo al tipo, título y contenido especificados.
     *
     * @param win       La ventana sobre la que se mostrará la alerta.
     * @param alertType El tipo de alerta a mostrar.
     * @param title     El título de la alerta.
     * @param content   El contenido de la alerta.
     */
    private void mostrarAlert(Window win, Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType); // Crea una nueva alerta
        alert.initOwner(win); // Establece la ventana principal
        alert.setHeaderText(null); // Sin encabezado
        alert.setTitle(title); // Establece el título
        alert.setContentText(content); // Establece el contenido
        alert.showAndWait(); // Muestra la alerta y espera a que se cierre
    }

    /**
     * Maneja el evento de eliminar una persona de la lista.
     * Verifica si se ha seleccionado una persona antes de intentar eliminar.
     *
     * @param event El evento que desencadena la acción.
     */
    @FXML
    void eliminar(ActionEvent event) {
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem(); // Obtiene la persona seleccionada

        if (personaSeleccionada != null) {
            confirmarEliminacion(event, personaSeleccionada); // Confirma la eliminación de la persona
        } else {
            mostrarAlertError(((Button) event.getSource()).getScene().getWindow(), "Por favor, selecciona una persona para eliminar."); // Muestra error si no hay selección
        }
    }

    /**
     * Confirma la eliminación de la persona seleccionada.
     *
     * @param event             El evento que desencadena la acción.
     * @param personaSeleccionada La persona que se va a eliminar.
     */
    private void confirmarEliminacion(ActionEvent event, Persona personaSeleccionada) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Crea una alerta de confirmación
        alert.setTitle("Confirmar eliminación"); // Título de la alerta
        alert.setHeaderText(null); // Sin encabezado
        alert.setContentText("¿Estás seguro de que deseas eliminar a " + personaSeleccionada.getNombre() + "?"); // Contenido de la alerta

        // Muestra la alerta y espera la respuesta
        if (alert.showAndWait().get() == ButtonType.OK) {
            personas.remove(personaSeleccionada); // Elimina la persona de la lista
        }
    }

    /**
     * Maneja el evento de modificar los datos de una persona seleccionada.
     * Verifica la validez de los datos ingresados antes de realizar la modificación.
     *
     * @param event El evento que desencadena la acción.
     */
    @FXML
    void modificar(ActionEvent event) {
        Persona personaSeleccionada = tabla.getSelectionModel().getSelectedItem(); // Obtiene la persona seleccionada

        if (personaSeleccionada != null) {
            String errores = verificarInfo(); // Verifica si hay errores en la información ingresada
            if (!errores.isEmpty()) {
                mostrarAlertError(((Button) event.getSource()).getScene().getWindow(), errores); // Muestra errores si los hay
                return;
            }

            confirmarModificacion(event, personaSeleccionada); // Confirma la modificación
        } else {
            mostrarAlertError(((Button) event.getSource()).getScene().getWindow(), "Por favor, selecciona una persona para modificar."); // Muestra error si no hay selección
        }
    }

    /**
     * Confirma la modificación de los datos de la persona seleccionada.
     *
     * @param event             El evento que desencadena la acción.
     * @param personaSeleccionada La persona cuyos datos se van a modificar.
     */
    private void confirmarModificacion(ActionEvent event, Persona personaSeleccionada) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Crea una alerta de confirmación
        alert.setTitle("Confirmar modificación"); // Título de la alerta
        alert.setHeaderText(null); // Sin encabezado
        alert.setContentText("¿Estás seguro de que deseas modificar a " + personaSeleccionada.getNombre() + "?"); // Contenido de la alerta

        // Muestra la alerta y espera la respuesta
        if (alert.showAndWait().get() == ButtonType.OK) {
            actualizarPersona(personaSeleccionada); // Actualiza la persona seleccionada
            tabla.refresh(); // Refresca la tabla para mostrar los cambios
            limpiarCampos(); // Limpia los campos después de modificar
            mostrarAlertInfo(((Button) event.getSource()).getScene().getWindow(), "Persona modificada correctamente"); // Muestra mensaje de éxito
        }
    }

    /**
     * Actualiza los datos de la persona seleccionada con los nuevos datos ingresados en los campos de texto.
     *
     * @param personaSeleccionada La persona cuyos datos se van a actualizar.
     */
    private void actualizarPersona(Persona personaSeleccionada) {
        personaSeleccionada.setNombre(txtNombre.getText()); // Actualiza el nombre
        personaSeleccionada.setApellido(txtApellido.getText()); // Actualiza el apellido
        personaSeleccionada.setEdad(Integer.parseInt(txtEdad.getText())); // Actualiza la edad
    }
}
