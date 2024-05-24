package edu.programacion.javafx.controller;

import java.util.List;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.service.UserService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UsersController {

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtLogName;

	@FXML
	private TextField txtDoc;

	@FXML

	private TextField txtEmail;
	@FXML
	private Button btnSave;

	@FXML
	private Button btnAddUser;

	private UserService userService;
	private List<User> users;
	private int currentIndex = 0;
	private User originalUser;

	private String logedUserRol= "standar";

	public UsersController() {
		userService = new UserService();
		loadUsers();
	}

	@FXML
	public void initialize() {
		btnSave.setDisable(true);
		btnAddUser.setDisable(logedUserRol.equals("administrador")? false: true);
		txtId.setDisable(true);
		loadUsers();
		showUserDetails(currentIndex);
		// addChangeListeners();
		addBindings();
	}

	private void loadUsers() {
		users = userService.getUsers();
	}

	private void showUserDetails(int index) {
		if (users != null && !users.isEmpty() && index >= 0 && index < users.size()) {
			originalUser = users.get(index);
			txtId.setText(String.valueOf(originalUser.getUserId()));
			txtName.setText(originalUser.getName());
			txtLogName.setText(originalUser.getAvatar());
			txtDoc.setText(originalUser.getPass());
			txtEmail.setText(originalUser.getEmail());
		}
	}

	@FXML
	private void showPreviousUser() {
		if (currentIndex > 0) {
			currentIndex--;
			showUserDetails(currentIndex);
		}
	}

	@FXML
	private void showNextUser() {
		if (currentIndex < users.size() - 1) {
			currentIndex++;
			showUserDetails(currentIndex);
		}
	}

	@FXML
	private void updateUser() {
		if (users != null && !users.isEmpty() && currentIndex >= 0 && currentIndex < users.size()) {
			User user = users.get(currentIndex);
			// user.setUserId(Integer.parseInt(txtId.getText()));
			user.setName(txtName.getText());
			user.setAvatar(txtLogName.getText());
			user.setPass(txtDoc.getText());
			user.setEmail(txtEmail.getText());
			userService.updateUser(user);
			showUserDetails(currentIndex);
		}
	}

	private void addChangeListeners() {
		ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
			boolean hasChanged = !txtName.getText().equals(originalUser.getName())
					|| !txtLogName.getText().equals(originalUser.getAvatar())
					|| !txtDoc.getText().equals(originalUser.getPass())
					|| !txtEmail.getText().equals(originalUser.getEmail());
			btnSave.setDisable(!hasChanged);
		};

		txtName.textProperty().addListener(changeListener);
		txtLogName.textProperty().addListener(changeListener);
		txtDoc.textProperty().addListener(changeListener);
		txtEmail.textProperty().addListener(changeListener);
	}

	// con bindings
	private void addBindings() {
		if (logedUserRol.equals("administrador")) {

			BooleanBinding fieldsChanged = Bindings.createBooleanBinding(
					() -> !txtName.getText().equals(originalUser.getName())
							|| !txtLogName.getText().equals(originalUser.getAvatar())
							|| !txtDoc.getText().equals(originalUser.getPass())
							|| !txtEmail.getText().equals(originalUser.getEmail()),

					txtName.textProperty(), txtLogName.textProperty(), txtDoc.textProperty(), txtEmail.textProperty());

			btnSave.disableProperty().bind(fieldsChanged.not());
		}

	}

	public void showNewUserForm() {

	}

	public void setLogedUserRol(String rol) {
		logedUserRol = rol;
	}

}
