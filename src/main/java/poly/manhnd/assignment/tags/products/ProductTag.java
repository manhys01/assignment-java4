package poly.manhnd.assignment.tags.products;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ProductTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	
	private int maxResult;
	private int startPosition;
	
	@Override
	public int doStartTag() throws JspException {
		String spage = pageContext.getRequest().getParameter("page");
		String find = pageContext.getRequest().getParameter("find");
		int page;
		if(spage==null || spage.isEmpty()) {
			page = 1;
			startPosition = page;
		}else {
			page = Integer.parseInt(spage) - 1;
			startPosition = page * maxResult + 1;
		}
		
		if(find==null||find.trim().isEmpty()) {
			find = "";
		}
		
		ProductDAO dao = new ProductDAOImp();
		
		try {
			List<Product> products = dao.getListProducts(find, startPosition, maxResult);
			int totalRecords = dao.getTotalProduct(find);
			pageContext.setAttribute("products", products);
			pageContext.setAttribute("totalRecords", totalRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
