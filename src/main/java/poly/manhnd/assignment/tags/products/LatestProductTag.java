package poly.manhnd.assignment.tags.products;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class LatestProductTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			ProductDAO dao = new ProductDAOImp();
			Product p = dao.getLatestProduct();
			pageContext.setAttribute("LatestProduct", p);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return SKIP_BODY;
	}

}
