package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@Column(name="from_id")
private String from;
	@Column(name="to_id")
private String to;
private char status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}

}
