package eu.andreatt.ejerciciod_dein.controller;

import eu.andreatt.ejerciciod_dein.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
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
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colEdad.setCellValueFactory(cellData -> cellData.getValue().edadProperty().asObject());
    }


    @FXML
    void agregarPersona(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow();
        //mostrarInputDialog(win);
        ventanaModal();
    }

    private void ventanaModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciod_dein/fxml/modalD.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del modal
            modalDController modalController = loader.getController();

            // Pasar la lista de personas al controlador del modal
            modalController.setPersonas(tabla.getItems());

            // Crear la nueva escena y la ventana modal
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.setWidth(300);
            newStage.setHeight(200);
            newStage.setScene(newScene);
            newStage.setTitle("Nueva Persona");

            // Mostrar la ventana modal y esperar a que se cierre
            newStage.showAndWait();

            // Actualizar la tabla una vez que se cierra el modal
            tabla.refresh();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}

