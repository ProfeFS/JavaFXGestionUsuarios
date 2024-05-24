package edu.programacion.javafx.JFXGestionUsuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import edu.programacion.javafx.utils.DatabaseConnection;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static final String BASE_PATH = "/edu/programacion/javafx/vista/";

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML(BASE_PATH + "Login"));
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
	
	 @Override
	    public void stop() {
	        // Realizar cualquier limpieza adicional aquí
	        handleExit();
	        System.out.println("Application is closing...");
	    }

	    private void handleExit() {
	        // Cerrar conexiones a la base de datos y liberar recursos
	        DatabaseConnection.closeConnection(); // Asegúrate de tener este método en tu clase Database
	        System.out.println("Resources have been cleaned up.");
	    }

	public static void main(String[] args) {
		launch();
	}

}