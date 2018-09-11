package poly.manhnd.assignment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private int id;

	@Column(name = "UserName", unique = true, length = 32, nullable = false, updatable = false)
	private String username;

	@Column(name = "Password", nullable = false, updatable = true, length = 32)
	private String password;

	@Column(name = "Name", nullable = false, length = 45)
	private String fullname;

	@Column(name = "Phone", nullable = false, length = 15)
	private String phone;

	@Column(name = "Address", nullable = false, length = 255)
	private String address;

	@Column(name = "Email", nullable = false, length = 255, unique = true)
	private String email;
	
	@Column(name="isAdmin")
	private boolean admin;
	
	@Column(name="created",insertable=false,updatable=false)
	private Date createAt;
	
	@Column(name="Blocked", insertable=false,updatable=true)
	private boolean blocked;

	public User() {
		super();
	}

	public User(String username, String password, String fullname, String phone, String address, String email) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", phone=" + phone + ", address=" + address + ", email=" + email + ", admin=" + admin + ", createAt="
				+ createAt + ", blocked=" + blocked + "]";
	}
}
