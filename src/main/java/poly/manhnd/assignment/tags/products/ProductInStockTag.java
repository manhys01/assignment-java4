package poly.manhnd.assignment.tags.products;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ProductInStockTag extends SimpleTagSupport {
	
	private int id;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		ProductDAO dao = new ProductDAOImp();
		try {
			System.out.println("Product id = " + id);
			Product p = dao.getProduct(id);
			out.println(p.getInStock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
