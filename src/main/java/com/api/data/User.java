package com.api.data;

public class User {
	private String id;  
	// we are not passing id in body for POSt req, BUT , Id will be generated once you create user. 
	//Hence, declare a variable but not passed in constructor
	private String name;
	private String email;
	private String gender;
	private String status;
	
	public User(String name, String email, String gender, String status) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [ getName()=" + getName() + ", getEmail()=" + getEmail() + ", getGender()="
				+ getGender() + ", getStatus()=" + getStatus() + "]";
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
}
