package poly.manhnd.assignment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;

	@Column(name = "Amount", nullable = false)
	private float amount;
	
	@Column(name = "CustomerName", nullable=false)
	private String customerName;
	
	@Column(name = "Shipping_Address",nullable=false)
	private String shippingAddress;

	@Column(name = "PhoneNumber", length = 15, nullable = false)
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "OrderState")
	private OrderState orderState;

	@Column(name = "Created", insertable = false, updatable = false)
	private Date createAt;

	@Column(name = "Updated", insertable = false, updatable = false)
	private Date latestUpdate;

	@Column(name = "Fixer", insertable=false, updatable=true)
	private String fixer;
	
	
	public Order() {
		super();
	}

	public Order(User user, float amount, String customerName, String shippingAddress, String phoneNumber,
			OrderState orderState) {
		super();
		this.user = user;
		this.amount = amount;
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.phoneNumber = phoneNumber;
		this.orderState = orderState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getFixer() {
		return fixer;
	}

	public void setFixer(String fixer) {
		this.fixer = fixer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", amount=" + amount + ", customerName=" + customerName
				+ ", shippingAddress=" + shippingAddress + ", phoneNumber=" + phoneNumber + ", orderState=" + orderState
				+ ", createAt=" + createAt + ", latestUpdate=" + latestUpdate + ", fixer=" + fixer + "]";
	}

}
