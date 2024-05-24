package edu.programacion.javafx.controller;

import java.io.IOException;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.service.UserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	private static final String PATH = "/edu/programacion/javafx/vista/";

	@FXML
	private TextField txtLogUser;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnSalir;

	private User user;
	private UserService userService;
	private String logedUserRol;

	private UsersController usersController;

	public LoginController() {
		userService = new UserService();
	}

	public void login(ActionEvent event) {
		user = userService.getUserByLoginName(txtLogUser.getText());
		if (user != null) {
			if (isValidPassword()) {
				showUserView(event);
			} else {
				showAlert("Pasword inválido");
			}
		} else {
			showAlert("Usuario inválido");
		}
	}

	private void showAlert(String string) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error de Inicio de Sesión");
		alert.setHeaderText(null);
		alert.setContentText(string);
		alert.showAndWait();
	}

	private void showUserView(ActionEvent event) {
		// Carga el nuevo contenido FXML
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Users.fxml"));
					Parent root;
					
					try {
						root = loader.load();
						usersController = loader.getController();
						usersController.setLogedUserRol(logedUserRol);
						// Obtiene el Stage actual a partir del evento del botón
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

						// Crea una nueva Scene con el nuevo root
						Scene scene = new Scene(root);

						// Establece la nueva Scene en el Stage
						stage.setScene(scene);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				

	}

	private boolean isValidPassword() {
		return user.getPass().equals(txtPassword);
	}

	public void stopApp() {
		Platform.exit();
	}

}
