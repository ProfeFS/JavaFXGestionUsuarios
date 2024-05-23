package edu.programacion.javafx.service;

import java.sql.SQLException;
import java.util.List;

import edu.programacion.javafx.dao.UserDAO;
import edu.programacion.javafx.dao.UserDaoImpl;
import edu.programacion.javafx.model.User;

public class UserService {

	private UserDAO userDao;

	public UserService() {
		// Inicialización del DAO. Podría inyectarse también si estás usando algún
		// framework de inyección de dependencias.
		this.userDao = new UserDaoImpl();
	}

	public List<User> getUsers() {
		try {
			return userDao.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Registra un nuevo usuario
	public void saveUser(User user) {
		try {
			// Aquí podrías agregar lógica de negocio adicional, como validación de datos.
			userDao.saveUser(user);
			System.out.println("Usuario registrado con éxito: " + user.getName());
		} catch (SQLException e) {
			System.out.println("Error al registrar el usuario: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error al registrar el usuario: " + e.getMessage());
		}
	}
	
	// Actualiza los datos de un usuario
		public void updateUser(User user) {
			try {
				//userDao.updateUser(user);
				System.out.println("Usuario actualizado con éxito: " + user.getName());
			} catch (Exception e) {
				System.out.println("Error al actualizar el usuario: " + e.getMessage());
			}
		}

		// Elimina un usuario por su ID
		public void deleteUser(int userId) {
			try {
				//userDao.deleteUser(userId);
				System.out.println("Usuario eliminado con éxito.");
			} catch (Exception e) {
				System.out.println("Error al eliminar el usuario: " + e.getMessage());
			}
		}
		

}
