package edu.programacion.javafx.controller;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.service.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
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

	public LoginController() {
		userService = new UserService();
	}

	public void login() {
		user = userService.getUserByLoginName(txtLogUser.getText());
		if (user != null) {
			if(isValidPassword()) {
				showUserView();
			}else {
				showAlert("Pasword inválido");
			}
		}else {
			showAlert("Usuario inválido");
		}
	}

	private void showAlert(String string) {
		// TODO Auto-generated method stub
		
	}

	private void showUserView() {
		// TODO Auto-generated method stub
		
	}

	private boolean isValidPassword() {
		return user.getPass().equals(txtPassword);
	}

	public void stopApp() {
		Platform.exit();
	}

}
