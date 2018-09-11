package poly.manhnd.assignment.tags.products;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ProductRandomTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private int limit;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			ProductDAO dao = new ProductDAOImp();
			List<Product> list = dao.getListRamdomProducts(limit);
			pageContext.setAttribute("randomProducts", list);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return SKIP_BODY;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
