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
    void agregarPersona(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow();
        //mostrarInputDialog(win);
        ventanaModal();
    }

    private void ventanaModal(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciod_dein/fxml/modalD.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.setWidth(300);
            newStage.setHeight(200);
            //newStage.initOwner(this.btnNuevo.getScene().getWindow());
            newStage.setScene(newScene);
            newStage.setTitle("Nueva Persona");
            newStage. showAndWait ();
            //this.cargarUsuarios(); //cuando se cierre la ventana modal se ejecutará está línea
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }



}
