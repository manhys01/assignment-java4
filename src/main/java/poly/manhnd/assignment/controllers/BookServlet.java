package poly.manhnd.assignment.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.carts.Cart;
import poly.manhnd.assignment.carts.ProductDTO;
import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.OrderDetailDAO;
import poly.manhnd.assignment.daos.entities.OrderStateDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;
import poly.manhnd.assignment.entities.OrderState;
import poly.manhnd.assignment.entities.Product;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.enums.Sort;
import poly.manhnd.assignment.info.Message;
import poly.manhnd.assignment.utils.MailUtilGmail;
import poly.manhnd.assignment.utils.StringUtils;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Message message = null;
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("Cart");
		try {
			// Thông tin cho Order
			User user = (User) session.getAttribute("User");
			if (user == null) {
				// Default
				user = new UserDAOImp().getUser(1);
			}
			float amount = Float.parseFloat(request.getParameter("amount"));

			// Tên khách hàng
			String customerName = request.getParameter("CustomerName");
			if (customerName.isEmpty()) {
				throw new Exception("Chưa có tên khách hàng!");
			}
			System.out.println("Tên khách hàng: " + customerName);
			session.setAttribute("customerName", customerName);

			// địa chỉ giao hàng
			String shippingAddress = request.getParameter("Shipping Address");
			if (shippingAddress.isEmpty()) {
				throw new Exception("Bạn chưa nhập địa chỉ giao hàng!");
			}
			System.out.println("Địa chỉ: " + shippingAddress);
			session.setAttribute("shippingAddress", shippingAddress);

			// Số điện thoại
			String phoneNumber = request.getParameter("Phone Number");
			if (phoneNumber.isEmpty()) {
				throw new Exception("Bạn chưa nhập số điện thoại!");
			}
			System.out.println("Số điện thoại: " + phoneNumber);
			session.setAttribute("phoneNumber", phoneNumber);

			if (!StringUtils.isNumberic(phoneNumber)) {
				throw new Exception("Số điện thoại bạn nhập không hợp lệ!");
			}

			// Trạng thái order (mặc định là chờ xác nhận)
			OrderStateDAO orderStateDAO = new OrderStateDAOImp();
			OrderState orderState = orderStateDAO.getOrderState(1);

			// Tạo order mới
			Order order = new Order(user, amount, customerName, shippingAddress, phoneNumber, orderState);
			OrderDAO orderDAO = new OrderDAOImp();
			boolean success = orderDAO.createOrder(order);
			if (success) {
				List<Order> orders = orderDAO.getAllOrders(Sort.ASC);
				order = orders.get(orders.size() - 1);
				System.out.println("Order: " + order);

				for (Entry<Integer, ProductDTO> c : cart.entrySet()) {
					Product product = c.getValue().getProduct();
					int quantity = c.getValue().getQuantity();
					OrderDetail orderDetail = new OrderDetail(product, order, quantity);
					OrderDetailDAO orderDetailDAO = new OrderDetailDAOImp();
					orderDetailDAO.createOrderDetail(orderDetail);
				}
				message = new Message(1, "Đặt hàng thành công!");
				session.setAttribute("Message", message);

				try {
					System.out.println("Gửi mail...");
					String email = "manhnd2261995@gmail.com";
					String to = email;
					String from = "manhnd2261995@gmail.com";
					String subject = "Có một đơn hàng mới";
					String body = "Có một đơn hàng mới vừa được đặt, vui lòng kiểm tra và xác thực lại đơn hàng!";
					boolean bodyIsHTML = false;
					MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
				response.sendRedirect("result.jsp");
			} else {
				throw new Exception("Đặt hàng thất bại... Thông tin bạn nhập có lỗi!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(Message.ERROR, e.getMessage());
			session.setAttribute("Message", message);
			response.sendRedirect("book.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
