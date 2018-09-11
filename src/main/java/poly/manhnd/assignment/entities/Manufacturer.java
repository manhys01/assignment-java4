package poly.manhnd.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Manufacturers")
public class Manufacturer {
	@Id
	private int id;

	@Column(name = "CompanyName", nullable = false, length = 45)
	private String name;

	public Manufacturer() {
		super();
	}

	public Manufacturer(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + "]";
	}

}
