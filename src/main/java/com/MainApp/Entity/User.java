package com.MainApp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class User {

	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private boolean status;
	@Column
	private String verification;
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public boolean isStatus() { return status; }
	public void setStatus(boolean status) { this.status = status; }

	public String getVerification() { return verification; }
	public void setVerification(String verification) { this.verification = verification; }
	
}
