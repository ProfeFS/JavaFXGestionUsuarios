package edu.programacion.javafx.controller;

import java.util.List;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ManageUserController {
	
	@FXML
    private TextField txtName;

    @FXML
    private TextField txtAvatar;

    @FXML
    private TextField txtDoc;

    @FXML
    private TextField txtEmail;

    private UserService userService;
    private List<User> users;
    private int currentIndex = 0;

    public ManageUserController() {
        userService = new UserService();
        loadUsers();
    }

    @FXML
    public void initialize() {
        loadUsers();
        showUserDetails(currentIndex);
    }

    private void loadUsers() {
        users = userService.getUsers();
    }

    private void showUserDetails(int index) {
        if (users != null && !users.isEmpty() && index >= 0 && index < users.size()) {
            User user = users.get(index);
            txtName.setText(user.getName());
            txtAvatar.setText(user.getAvatar());
            txtDoc.setText(user.getDoc());
            txtEmail.setText(user.getEmail());
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
    private void handleModifyUser() {
        if (users != null && !users.isEmpty() && currentIndex >= 0 && currentIndex < users.size()) {
            User user = users.get(currentIndex);
            user.setName(txtName.getText());
            user.setAvatar(txtAvatar.getText());
            user.setDoc(txtDoc.getText());
            user.setEmail(txtEmail.getText());
            userService.saveUser(user);
            showUserDetails(currentIndex); 
        }
    }

}
