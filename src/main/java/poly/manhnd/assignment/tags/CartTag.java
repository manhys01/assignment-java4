package poly.manhnd.assignment.tags;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import poly.manhnd.assignment.carts.Cart;
import poly.manhnd.assignment.carts.ProductDTO;
import poly.manhnd.assignment.entities.Product;

public class CartTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private Cart cart;
	private ProductDTO item;
	private Iterator<Integer> iterator;
	private Set<Integer> set;
	private float totalAmount;
	private int count;

	@Override
	public int doStartTag() throws JspException {
		cart = (Cart) pageContext.findAttribute("Cart");
		if (cart == null || cart.isEmpty()) {
			return SKIP_BODY;
		} else {
			return EVAL_BODY_BUFFERED;
		}
	}

	@Override
	public void doInitBody() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			
			out.print("<div class='container pt-5 pb-5'>");
			out.print(
					"<nav aria-label='breadcrumb'>" + 
					"	<ol class='breadcrumb'>" + 
					"		<li class='breadcrumb-item'>Giỏ hàng</li>" + 
					"	</ol>" + 
					"</nav>");
			
			out.print("<table class=\"table table-striped table-bordered\">");
			out.print("<thead><tr>"
						+ "<th>#</th>"
						+ "<th>Hình</th>"
						+ "<th>Mã sản phẩm</th>"
						+ "<th>Tên sản phẩm</th>"
						+ "<th>Số lượng</th>"
						+ "<th>Đơn giá</th>"
						+ "<th>Tổng</th>"
						+ "<th>Lựa chọn</th>"
					+ "</tr></thead>");
			out.print("<tbody>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		set = cart.keySet();
		iterator = set.iterator();
		// In ra dòng đầu tiên
		if (iterator.hasNext()) {
			int key = iterator.next();
			item = cart.get(key);
			count = 1;
			totalAmount = 0;
			this.setItemAttribute(item);
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			// Nếu còn dữ liệu tiếp tục lặp
			if (iterator.hasNext()) {
				int key = iterator.next();
				item = cart.get(key);
				this.setItemAttribute(item);
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

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		DecimalFormat formatter = new DecimalFormat("#,###");
		try {
			if(cart.isEmpty()) {
				return SKIP_PAGE;
			}
			String total = formatter.format(totalAmount).replaceAll(",", ".");
			out.print("<td colspan='2'><a href=\"showproduct.jsp\" class=\"btn btn-block btn-success\">Tiếp tục mua hàng</a></td>");
			out.print("<td colspan='4' class='text-right'>Thành tiền</td>");
			out.print("<td>" + total + "</td>");
			out.print("<td>" + 
					  "	<form action=\"book.jsp\" method=\"post\">" + 
					  "		<button value=\"Pay\" class=\"btn btn-info btn-block\">Đặt Hàng</button>" + 
					  "	</form>" + 
					  "</td>");
			out.print("</tbody>");
			out.print("</table>");
			out.print("</div>");
			return EVAL_PAGE;
		} catch (IOException e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
	}

	public void setItemAttribute(ProductDTO item) {
		Product product = item.getProduct();
		float amount = item.getQuantity() * product.getPrice();
		totalAmount += amount;
		pageContext.setAttribute("productID", product.getId());
		pageContext.setAttribute("productName", product.getName());
		pageContext.setAttribute("productImage", product.getImage());
		pageContext.setAttribute("description", product.getDescription());
		pageContext.setAttribute("price", product.getPrice());
		pageContext.setAttribute("inStock", product.getInStock());
		pageContext.setAttribute("quantity", item.getQuantity());
		pageContext.setAttribute("amount", amount);
		pageContext.setAttribute("totalAmount", totalAmount);
		pageContext.setAttribute("count", count++);
	}

}
