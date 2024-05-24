package edu.programacion.javafx.model;

public class User {
	
	private int userId;
	private String name;
	private String avatar;
	private String doc;
	private String email;
	
	public User(int id, String name, String avatar, String doc, String email) {
		userId = id;
		this.name = name;
		this.avatar = avatar;
		this.doc = doc;
		this.email = email;
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

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", avatar=" + avatar + ", doc=" + doc + ", email=" + email
				+ "]";
	}
	
	

	

}
