package poly.manhnd.assignment.tags.orders;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.OrderDetailDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;

public class OrderDetailTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			OrderDAO orderDao = new OrderDAOImp();
			Order order = orderDao.getOrder(orderId);
			
			OrderDetailDAO orderDetailDAO = new OrderDetailDAOImp();
			List<OrderDetail> list = orderDetailDAO.findOrderDetailsByOrder(order);
			
			pageContext.setAttribute("Order", order);
			pageContext.setAttribute("OrderDetails", list);
			pageContext.setAttribute("sizeOfOrderDetail", list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
}
