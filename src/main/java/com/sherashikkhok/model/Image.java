package com.sherashikkhok.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "user_Id")
	private Long userId;

    @Column(name ="user_email")
	private String email;
    
    @Lob
    @NotNull
    private byte[] data;
    
    @NotNull
    private String mimetype;
    
    @NotNull
    private String imageName;
    
    @Transient
    private String userName;


	public Image() {
    }
    
	public Image(Long userId, String email, byte[] data, String mimetype, String imageName) {
		super();
		this.userId = userId;
		this.email = email;
		this.data = data;
		this.mimetype = mimetype;
		this.imageName = imageName;
	}


   public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Image(byte[] data, String mimetype) {
        this.data = data;
        this.mimetype = mimetype;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
    
    
}