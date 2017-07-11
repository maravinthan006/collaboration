package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Register")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@Column(unique=true,nullable=false)
 private String username;
	private String name;
 private String password;
private String phonenumber;
@Column(unique=true,nullable=false)
private String email;
private String role;
private boolean enabled;
@Column(name="on_line")
private boolean online;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public boolean isOnline() {
	return online;
}
public void setOnline(boolean online) {
	this.online = online;
}

}