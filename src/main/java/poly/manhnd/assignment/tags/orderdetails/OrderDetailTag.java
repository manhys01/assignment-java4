package poly.manhnd.assignment.tags.orderdetails;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.OrderDetailDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.entities.OrderDetail;

public class OrderDetailTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			String detailId = pageContext.getRequest().getParameter("orderDetailId");
			int id  = Integer.parseInt(detailId);
			OrderDetailDAO dao = new OrderDetailDAOImp();
			OrderDetail detail = dao.getOrderDetail(id);
			pageContext.setAttribute("detail", detail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
}
