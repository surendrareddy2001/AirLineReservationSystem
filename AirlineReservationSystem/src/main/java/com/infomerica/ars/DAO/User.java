package com.infomerica.ars.DAO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AIRUSER")
public class User {

    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FULLNAME")
	private String fullName;
    @Column(name = "USERNAME")
	private String userName;
    @Column(name = "PHONENUMBER")
   	private Long phoneNumber;
    @Column(name = "PASSWORD")
	private String password;
   
    
	public User(String fullName, String userName, String email, String password, Long phoneNumber) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public User() {
		
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", fullName=" + fullName + ", userName=" + userName + ", phoneNumber="
				+ phoneNumber + ", password=" + password + "]";
	}
	
}
