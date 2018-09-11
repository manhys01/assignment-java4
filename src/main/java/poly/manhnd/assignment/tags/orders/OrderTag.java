package poly.manhnd.assignment.tags.orders;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;
import poly.manhnd.assignment.entities.OrderState;
import poly.manhnd.assignment.enums.Sort;

public class OrderTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private int startPosition;
	private int maxResult;
	private int totalRecords;
	private List<Order> orders;
	private List<OrderDetail> details;
	Iterator<Order> iterator;
	private List<OrderState> states;

	@Override
	public int doStartTag() throws JspException {
		String search = pageContext.getRequest().getParameter("search");
		try {
			totalRecords = new OrderDAOImp().getTotalOrder(search);
			if (totalRecords > 0) {
				pageContext.setAttribute("totalRecords", totalRecords);
				return EVAL_BODY_BUFFERED;
			}else {
				pageContext.getOut().println("<h3>Không tìm thấy order nào với từ khóa "+ search +"!</h3>");
				return SKIP_BODY;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public void doInitBody() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			out.println(
					"<div class=\"table-responsive\">\r\n" + 
					"	<table\r\n" + 
					"		class=\"table table-sm table-striped table-bordered table-hover\">\r\n" + 
					"		<thead>\r\n" + 
					"			<tr>\r\n" + 
					"				<th>Mã đơn hàng</th>\r\n" + 
					"				<th>Tài khoản</th>\r\n" + 
					"				<th>Tên khách hàng</th>\r\n" + 
					"				<th>Sản phẩm</th>\r\n" + 
					"				<th>Số lượng SP</th>\r\n" + 
					"				<th>Tổng tiền</th>\r\n" + 
					"				<th>Điện thoại liên hệ</th>\r\n" + 
					"				<th>Địa chỉ giao hàng</th>\r\n" + 
					"				<th>Thời gian đặt hàng</th>\r\n" + 
					"				<th>Tình trạng đơn hàng</th>\r\n" + 
					"				<th>Lựa chọn</th>\r\n" + 
					"			</tr>\r\n" + 
					"		</thead>\r\n" + 
					"		<tbody>");
			
			String spage = pageContext.getRequest().getParameter("page");
			String search = pageContext.getRequest().getParameter("search");
			
			int page;
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage) - 1;
				startPosition = page * maxResult + 1;
			}
						
			orders = new OrderDAOImp().getListOrders(Sort.DESC, search, startPosition, maxResult);
			iterator = orders.iterator();
			if (iterator.hasNext()) {
				Order order = iterator.next();
				details = new OrderDetailDAOImp().findOrderDetailsByOrder(order);
				states = new OrderStateDAOImp().getAllOrderStates();
				this.setOrderAttribute(order);
				this.setOrderDetailsAttribute(details);
				this.setOrderStateAttribute(states);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			if (iterator.hasNext()) {
				Order order = iterator.next();
				details = new OrderDetailDAOImp().findOrderDetailsByOrder(order);
				this.setOrderAttribute(order);
				this.setOrderDetailsAttribute(details);
				return EVAL_BODY_AGAIN;
			} else {
				JspWriter out = bodyContent.getEnclosingWriter();
				bodyContent.writeOut(out);
				return SKIP_BODY;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print("</tbody></table></div>");
			return EVAL_PAGE;
		} catch (Exception e) {
			return SKIP_PAGE;
		}
	}

	private void setOrderAttribute(Order order) {
		pageContext.setAttribute("Order", order);
	}

	private void setOrderDetailsAttribute(List<OrderDetail> orderDetails) {
		pageContext.setAttribute("OrderDetails", orderDetails);
	}

	private void setOrderStateAttribute(List<OrderState> states) {
		pageContext.setAttribute("OrderStates", states);
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
