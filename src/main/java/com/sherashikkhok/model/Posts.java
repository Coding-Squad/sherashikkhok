package com.sherashikkhok.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_Id")
	private Long id;

	@Column(name = "post_title", nullable = false)
	@NotEmpty(message = "*Please provide a title")
	private String title;

	@Lob
	@Column(name = "post_body", length = 65535)
	@NotEmpty(message = "*Please provide contents")
	private String body;

	@Column(name = "user_Id", nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Date date = new Date();
	
	@Column(name ="user_email", nullable = false)
	private String email;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Posts(Long id, String title, String body, Set<User> user, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;

		this.date = date;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Posts() {
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", title=" + title + ", body=" + body + ", userId=" + userId + ", date=" + date
				+ "]";
	}

}
