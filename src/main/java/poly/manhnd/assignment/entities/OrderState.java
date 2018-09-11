package poly.manhnd.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderStates")
public class OrderState {
	
	@Id
	private int id;
	
	@Column(name="Name",length=45)
	private String name;

	public OrderState() {
		super();
	}

	public OrderState(String name) {
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
		return "OrderState [id=" + id + ", name=" + name + "]";
	}

}
