package edu.programacion.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.programacion.javafx.model.User;
import edu.programacion.javafx.utils.DatabaseConnection;

public class UserDaoImpl implements UserDAO {

	Connection conn;

	public UserDaoImpl() {
		conn = DatabaseConnection.getConnection();
	}

	@Override
	public List<User> getAllUsers() throws SQLException {

		List<User> users = new ArrayList<>();

		String querySelect = "SELECT * FROM users";
		PreparedStatement stmt = conn.prepareStatement(querySelect); // cargo la sentencia con la consula.
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println("ID: " + rs.getInt("userid") + ", Nombre: " + rs.getString("name"));
			User u = new User(rs.getInt("userid"), rs.getString("name"), rs.getString("avatar"), rs.getString("doc"), rs.getString("email"));
			users.add(u);
		}
		return users;
	}

	@Override
	public void saveUser(User user) throws SQLException {
		String insertSQL = "INSERT INTO users (name, avatar, doc, email) VALUES (?, ?, ?, ?)";

		try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, user.getName());
			insertStmt.setString(2, user.getAvatar());
			insertStmt.setString(1, user.getDoc());
			insertStmt.setString(2, user.getEmail());
			int insertCount = insertStmt.executeUpdate();
			ResultSet rs = insertStmt.getGeneratedKeys();

			if (insertCount != 0) {
				System.out.println("se ha creado el usuario en la bdd con id: " + rs.getInt("id"));
			}
		}
	}
	
	@Override
	public void updateUser(User user) throws SQLException {
		String updateSQL = "UPDATE users SET doc = ?, name = ?, avatar = ?, email = ? WHERE userid = ?";
		PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
		updateStmt.setString(1, user.getDoc());
		updateStmt.setString(2, user.getName());
		updateStmt.setString(3, user.getAvatar());
		updateStmt.setString(4, user.getEmail());		
		updateStmt.setInt(5, user.getUserId());
		
		int updateCount = updateStmt.executeUpdate();
		System.out.println("Se han actualizado " + updateCount + " registros" );

	}

}
