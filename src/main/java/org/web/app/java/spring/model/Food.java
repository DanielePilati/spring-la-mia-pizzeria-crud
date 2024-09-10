package org.web.app.java.spring.model;

import java.text.NumberFormat;
import java.time.Instant;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name = "food_name", nullable = false)
	private String name;

	@NotEmpty
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "img_url", columnDefinition = "LONGTEXT")
	private String imgUrl;

	@NotNull
	@Min(0)
	@Column(name = "food_price", nullable = false)
	private Double price;

	@UpdateTimestamp
	private Instant updatedAt;

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getPriceFormatted() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(price);
	}
	
}
