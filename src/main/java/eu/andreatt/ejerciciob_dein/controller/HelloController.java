package eu.andreatt.ejerciciob_dein.controller;

import eu.andreatt.ejerciciob_dein.model.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    protected void agregarPersona() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        int edad = Integer.parseInt(txtEdad.getText());

        Persona nuevaPersona = new Persona(nombre, apellido, edad);
        personas.add(nuevaPersona);

        // Limpiar los campos
        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
    }
}
