
package com.foodiefly.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="user")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
	
	@NotNull(message = "Name cannot be null")
	@Column(name="name")
	private String name;
	@NotNull(message = "username cannot be null")
	@Column(name="username")
	private String username;
	@NotNull(message = "password cannot be null")
	@Column(name="password")
	private String password;
	@NotNull(message = "email cannot be null")
	@Email(message = "Email should be valid")
	@Column(name="email")
	private String email;
	@Column(name="phone")
	@NotNull(message = "phone cannot be null")
	@Pattern(regexp = "^\\d{10}$", message = "Phone must be a 10-digit number")
	private String phone;
	@Column(name="address")
	private String address;
	@Column(name="role")
	private String role;
	@Column(name="createdDate")
	private LocalDateTime  createdDate;
	@Column(name="lastLoginDate")
	private LocalDateTime  lastLoginDate;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="user_Restaurant",
	joinColumns = @JoinColumn(name="userId"), inverseJoinColumns = @JoinColumn(name="restaurantId"))
	private List<Restaurant> restaurants;
	
	
	@PrePersist
	protected void onCreate() {
	    System.out.println("PrePersist: Setting createdDate");
	    if (createdDate == null) {
	        createdDate = LocalDateTime.now();
	        System.out.println("createdDate set to: " + createdDate);
	    }
	}

	@PreUpdate
	protected void onUpdate() {
	    System.out.println("PreUpdate: Setting lastLoginDate");
	    lastLoginDate = LocalDateTime.now();
	    System.out.println("lastLoginDate set to: " + lastLoginDate);
	}
    
    public User() {
		// TODO Auto-generated constructor stub
	}
	


	public User(String name, String username, String password, String email, String phone, String address,
			String role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
//		this.createdDate = createdDate;
//		this.lastLoginDate = lastLoginDate;
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

	public String getUsername() {
		return username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime  getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate ) {
		this.createdDate = createdDate;
	}

	public LocalDateTime  getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime  lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdDate="
				+ createdDate + ", lastLoginDate=" + lastLoginDate + "]";
	}
	
	
}




















