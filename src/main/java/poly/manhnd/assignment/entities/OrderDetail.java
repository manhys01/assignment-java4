package poly.manhnd.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "ProductID", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "OrderID", nullable = false)
	private Order order;

	@Column(name = "Quantity")
	private int quantity;

	public OrderDetail() {
		super();
	}

	public OrderDetail(Product product, Order order, int quantity) {
		super();
		this.product = product;
		this.order = order;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product=" + product + ", order=" + order + ", quantity=" + quantity + "]";
	}

}
