package com.niit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="blogpost")
public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private Date createdOn;
@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name="created_by_id")
private User createdBy;
private String title;
@Lob
@Column(name="blog_body")
private String body;

private boolean approved;

@OneToMany(mappedBy="blogPost",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JsonIgnore
private List<BlogComment> blogComments=new ArrayList<BlogComment>();

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}
public User getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public boolean isApproved() {
	return approved;
}
public void setApproved(boolean approved) {
	this.approved = approved;
}
public List<BlogComment> getBlogComments() {
	return blogComments;
}
public void setBlogComments(List<BlogComment> blogComments) {
	this.blogComments = blogComments;
}

}
