package poly.manhnd.assignment.carts;

import java.util.LinkedHashMap;


public class Cart extends LinkedHashMap<Integer, ProductDTO> {

	private static final long serialVersionUID = 1L;
	
	public void addToCart(ProductDTO p) {
		int key = p.getProduct().getId();
		if(this.containsKey(key)) {
			int oldQuantity = this.get(key).getQuantity();
			this.get(key).setQuantity(oldQuantity+1);
		}else {
			this.put(p.getProduct().getId(), p);
		}
	}
	
	public boolean removeProduct(int id) {
		if(this.containsKey(id)) {
			this.remove(id);
			return true;
		}
		return false;
	}
	
}
