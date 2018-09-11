package poly.manhnd.assignment.tags.orders;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;

public class CountWaitingOrderTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		try {
			OrderDAO dao = new OrderDAOImp();
			int totalOrderWaiting = dao.getTotalWaitingOrder();
			pageContext.setAttribute("totalNumberOfWaitingOrder", totalOrderWaiting);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return SKIP_BODY;
	}
	
}
