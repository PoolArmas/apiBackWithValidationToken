package com.spring.jpa.h2.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "SKU")
	private String sku;

	@NotBlank(message = "Short Description of the Prodtuc")
	@Column(name = "Name")
	@Size(min = 3 , max = 50)
	private String name;
	
	@NotBlank(message = "Name of the Brand")
	@Column(name = "Brand")
	@Size(min = 3 , max = 50)
	private String brand;
	
	@NotBlank(message = "size of the Product")
	@Column(name = "Size")
	private String size;
	
	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Principal_Imagen")
	private String principalImagen;

	@Column(name = "Other_Imagen")
	private String otherImagen;

	public Product() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPrincipalImagen() {
		return principalImagen;
	}

	public void setPrincipalImagen(String principalImagen) {
		this.principalImagen = principalImagen;
	}

	public String getOtherImagen() {
		return otherImagen;
	}

	public void setOtherImagen(String otherImagen) {
		this.otherImagen = otherImagen;
	}



	public Product(long id, String sku,
			@NotBlank(message = "Short Description of the Prodtuc") @Size(min = 3, max = 50) String name,
			@NotBlank(message = "Name of the Brand") @Size(min = 3, max = 50) String brand,
			@NotBlank(message = "size of the Product") String size, BigDecimal price, String principalImagen,
			String otherImagen) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.principalImagen = principalImagen;
		this.otherImagen = otherImagen;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", sku=" + sku + ", name=" + name + ", brand=" + brand + ", size=" + size
				+ ", price=" + price + ", principalImagen=" + principalImagen + ", otherImagen=" + otherImagen + "]";
	}
	
	


}
