package com.foodiefly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="menu_items")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="menuId")
	private int menuId;
	@Column(name="itemName", nullable = false, length = 100)
	private String itemName;
	@Column(name="description")
	private String description;
	@Column(name="price", nullable = false)
	@Min(value = 0, message = "Price cannot be negative")
	private int price;
	@Column(name="rating")
	@Min(value = 0, message = "Rating cannot be negative")
	@Max(value = 5, message = "Rating cannot exceed 5")
	private float rating;
	@Column(name="isAvailable")
	private boolean isAvailable;
	@Column(name="imagepath")
	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "menu_restaurant")
	private Restaurant restaurant;
	
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

//	public Menu(String itemName, String description, int price, float rating, boolean isAvailable,
//			String imagePath) {
//		super();
//		this.itemName = itemName;
//		this.description = description;
//		this.price = price;
//		this.rating = rating;
//		this.isAvailable = isAvailable;
//		this.imagePath = imagePath;
//	}

	public Menu( String itemName, String description,
			@Min(value = 0, message = "Price cannot be negative") int price,
			@Min(value = 0, message = "Rating cannot be negative") @Max(value = 5, message = "Rating cannot exceed 5") float rating,
			boolean isAvailable, String imagePath, Restaurant restaurant) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
		this.restaurant = restaurant;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", itemName=" + itemName + ", description=" + description + ", price=" + price
				+ ", rating=" + rating + ", isAvailable=" + isAvailable + ", imagePath=" + imagePath + "]";
	}
	
	
}


