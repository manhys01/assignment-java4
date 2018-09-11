package poly.manhnd.assignment.tags.products;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ShowProductTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private Iterator<Product> iterator;
	private int page;
	private int startPosition;
	private int maxResult;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			String spage = pageContext.getRequest().getParameter("page");
			String param = pageContext.getRequest().getParameter("search");
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage);
				startPosition = page - 1;
				startPosition = startPosition * maxResult + 1;
			}
			System.out.println("request: " + spage);
			
			// Tìm kiếm hiển thị sản phẩm
			ProductDAO dao = new ProductDAOImp();
			
			int total = dao.getTotalProduct(param);
			products = dao.getListProducts(param, startPosition, maxResult);
			
			pageContext.setAttribute("totalRecords", total);
			if(products == null || products.isEmpty()) {
				System.out.println("Không tìm thấy..");
				pageContext.getOut().print("<div class='col-md-12'><h3>Không tìm thấy sản phẩm nào có tên là " + param +"</h3></div>");
				return SKIP_BODY;
			}
			return EVAL_BODY_BUFFERED;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return SKIP_BODY;
		}
	}

	@Override
	public void doInitBody() throws JspException {
		System.out.println("Init");
		try {
			iterator = products.iterator();
			if(iterator.hasNext()) {
				Product product = iterator.next();
				this.setProductAttribute(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e);
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		System.out.println("afterBody");
		JspWriter out = pageContext.getOut();
		try {
			if (iterator.hasNext()) {
				Product product = iterator.next();
				this.setProductAttribute(product);
				return EVAL_BODY_AGAIN;
			} else {
				out = bodyContent.getEnclosingWriter();
				bodyContent.writeOut(out);
				return SKIP_BODY;
			}
		} catch (Exception e) {
			System.err.println("error in doAfterBody " + e.getMessage());
			return SKIP_BODY;
		}
	}

	private void setProductAttribute(Product product) {
		pageContext.setAttribute("Product", product);
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	
}
