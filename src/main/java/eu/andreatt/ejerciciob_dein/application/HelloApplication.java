package eu.andreatt.ejerciciob_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/eu/andreatt/ejerciciob_dein/fxml/ejercicioB.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 450);
        stage.setMinHeight(300);
        stage.setMinWidth(350);
        stage.setTitle("PERSONAS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}