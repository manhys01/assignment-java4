package poly.manhnd.assignment.tags.orders;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.User;

public class CountOrderUserTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private User user;
	@Override
	public int doStartTag() throws JspException {
		OrderDAO dao = new OrderDAOImp();
		try {
			List<Order> orders = dao.findOrders(user);
			pageContext.setAttribute("totalOrder", orders.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
