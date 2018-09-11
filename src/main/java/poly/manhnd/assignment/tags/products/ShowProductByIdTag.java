package poly.manhnd.assignment.tags.products;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ShowProductByIdTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int productId;
	
	@Override
	public int doStartTag() throws JspException {
		ProductDAO dao = new ProductDAOImp();
		try {
			Product p = dao.getProduct(productId);
			pageContext.setAttribute("Product", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
