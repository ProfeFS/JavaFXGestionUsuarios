package edu.programacion.javafx.model;

public class User {
	
	private int userId;
	private String name;
	private String avatar;
	private String pass;
	private String email;
	private String rol;
	
	public User(int id, String name, String avatar, String pass, String email, String rol) {
		userId = id;
		this.name = name;
		this.avatar = avatar;
		this.pass = pass;
		this.email = email;
		this.rol = rol;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", avatar=" + avatar + ", pass=" + pass + ", email=" + email
				+ ", rol=" + rol + "]";
	}	

}
