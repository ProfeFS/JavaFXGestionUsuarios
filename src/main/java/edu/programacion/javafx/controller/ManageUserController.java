package edu.programacion.javafx.controller;

import java.util.List;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.service.UserService;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageUserController {

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

	private UserService userService;
	private List<User> users;
	private int currentIndex = 0;
	 private User originalUser;
	

	public ManageUserController() {
		userService = new UserService();
		loadUsers();
	}

	@FXML
	public void initialize() {
		btnSave.setDisable(true);
		txtId.setDisable(true);
		loadUsers();
		showUserDetails(currentIndex);
		addChangeListeners();
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
			txtDoc.setText(originalUser.getDoc());
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
			user.setDoc(txtDoc.getText());
			user.setEmail(txtEmail.getText());
			userService.updateUser(user);
			showUserDetails(currentIndex);
		}
	}
	
	private void addChangeListeners() {
        ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
            boolean hasChanged = !txtName.getText().equals(originalUser.getName()) ||
                                 !txtLogName.getText().equals(originalUser.getAvatar()) ||
                                 !txtDoc.getText().equals(originalUser.getDoc()) ||
                                 !txtEmail.getText().equals(originalUser.getEmail());
            btnSave.setDisable(!hasChanged);
        };

        txtName.textProperty().addListener(changeListener);
        txtLogName.textProperty().addListener(changeListener);
        txtDoc.textProperty().addListener(changeListener);
        txtEmail.textProperty().addListener(changeListener);
    }

}
