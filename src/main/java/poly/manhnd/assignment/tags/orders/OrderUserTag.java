package poly.manhnd.assignment.tags.orders;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.enums.Sort;

public class OrderUserTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private User user;
	private int count;
	private List<Order> orders;
	private List<OrderDetail> details;
	private Iterator<Order> iterator;

	private int startPosition;
	private int maxResult;

	private int totalRecords;

	@Override
	public int doStartTag() throws JspException {
		if (user == null) {
			return SKIP_BODY;
		} else {
			return EVAL_BODY_BUFFERED;
		}
	}

	@Override
	public void doInitBody() throws JspException {
		OrderDAO dao = new OrderDAOImp();
		try {
			JspWriter out = pageContext.getOut();
			count = 0;
			String spage = pageContext.getRequest().getParameter("page");
			int page;
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage) - 1;
				startPosition = page * maxResult + 1;
			}
			
			//Lấy về tổng số order của user đã order 
			totalRecords = dao.findOrders(user).size();
			pageContext.setAttribute("totalRecords", totalRecords);
			
			// tìm order của user sử dụng phân trang
			orders = dao.findOrders(Sort.DESC, user, startPosition, maxResult);
			
			iterator = orders.iterator();
			if (iterator.hasNext()) {
				out.print("<table class=\"table table-bordered table-hover\">\r\n" + "	<thead>\r\n"
						+ "		<tr>\r\n" + "			<th>#</th>\r\n" + "			<th>Mã đơn hàng</th>\r\n"
						+ "			<th>Các sản phẩm</th>\r\n" + "			<th>Số lượng sản phẩm</th>\r\n"
						+ "			<th>Tổng tiền</th>\r\n" + "			<th>Thời gian đặt hàng</th>\r\n"
						+ "			<th>Trạng thái đơn hàng</th>\r\n" + "		</tr>\r\n" + "</thead><tbody>");
				Order order = iterator.next();
				count = 1;
				details = new OrderDetailDAOImp().findOrderDetailsByOrder(order);
				pageContext.setAttribute("Order", order);
				pageContext.setAttribute("OrderDetails", details);
				pageContext.setAttribute("count", count);
			} else {
				out.print("Bạn chưa đặt hàng lần nào!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			if (iterator.hasNext()) {
				Order order = iterator.next();
				count++;
				details = new OrderDetailDAOImp().findOrderDetailsByOrder(order);
				pageContext.setAttribute("Order", order);
				pageContext.setAttribute("OrderDetails", details);
				pageContext.setAttribute("count", count);
				return EVAL_BODY_AGAIN;
			} else {
				JspWriter out = bodyContent.getEnclosingWriter();
				bodyContent.writeOut(out);
				return SKIP_BODY;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			if (orders.size() > 0) {
				JspWriter out = pageContext.getOut();
				out.print("</tbody></table>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
