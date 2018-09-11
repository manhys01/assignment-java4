package poly.manhnd.assignment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "CategoryID", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "ManufacturerID", nullable = false)
	private Manufacturer manufacturer;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private float price;

	@Column(name = "Image", length = 300)
	private String image;

	@Column(name = "InStock")
	private int inStock;

	@Column(name = "discontinued")
	private boolean discontinued;

	@Column(name = "description")
	private String description;

	@Column(name = "created", insertable = false, updatable = false)
	private Date createAt;

	@Column(name = "LatestUpdate", insertable = false, updatable = false)
	private Date latestUpdate;

	public Product() {
		super();
	}
	

	public Product(Category category, Manufacturer manufacturer, String name, float price, String image, int inStock,
			boolean discontinued, String description) {
		super();
		this.category = category;
		this.manufacturer = manufacturer;
		this.name = name;
		this.price = price;
		this.image = image;
		this.inStock = inStock;
		this.discontinued = discontinued;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(Date latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", manufacturer=" + manufacturer + ", name=" + name
				+ ", price=" + price + ", image=" + image + ", inStock=" + inStock + ", discontinued=" + discontinued
				+ ", description=" + description + ", createAt=" + createAt + ", latestUpdate=" + latestUpdate + "]";
	}

}
