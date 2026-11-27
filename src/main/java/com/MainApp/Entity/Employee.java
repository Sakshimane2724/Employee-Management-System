package com.MainApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private String designation;
	private String department;
	private String imageName;
	private double salary;
	private String date;
	private String project;
	private String username;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }

	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }

	public String getDesignation() { return designation; }
	public void setDesignation(String designation) { this.designation = designation; }

	public String getDepartment() { return department; }
	public void setDepartment(String department) { this.department = department; }

	public String getImageName() { return imageName; }
	public void setImageName(String imageName) { this.imageName = imageName; }

	public double getSalary() { return salary; }
	public void setSalary(double salary) { this.salary = salary; }

	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }

	public String getProject() { return project; }
	public void setProject(String project) { this.project = project; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
}
