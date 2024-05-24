package edu.programacion.javafx.dao;

import java.sql.SQLException;
import java.util.List;

import edu.programacion.javafx.model.User;

public interface UserDAO {
	List<User> getAllUsers() throws SQLException;
	void saveUser(User user) throws SQLException;
	void updateUser(User user) throws SQLException; // Actualiza los datos de un usuario
	User getUserByLoginName(String loginName) throws SQLException;;

}
