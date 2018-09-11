package poly.manhnd.assignment.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.OrderDetailDAO;
import poly.manhnd.assignment.daos.entities.OrderStateDAO;
import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderDetailDAOImp;
import poly.manhnd.assignment.daos.entities.implement.OrderStateDAOImp;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;
import poly.manhnd.assignment.entities.OrderState;
import poly.manhnd.assignment.entities.Product;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;

@WebServlet("/OrderManagerServlet")
public class OrderManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderManagerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action == null) {
			response.sendRedirect("order.jsp");
		} else {
			HttpSession session = request.getSession();
			switch (action) {
			case "view detail":

				break;
			case "update order":
				System.out.println("Sửa order");
				updateOrder(request, response, session);
				break;
			case "delete order":
				System.out.println("Xóa order");
				deleteOrder(request, response, session);
				break;
			case "update order detail":
				System.out.println("Sửa order detail");
				updateOrderDetail(request, response, session);
				break;
			case "delete order detail":
				System.out.println("Xóa order detail");
				deleteOrderDetail(request, response, session);
				break;
			default:
				break;
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Sửa order
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	private void updateOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		Message message = null;
		try {
			String id = request.getParameter("orderId");
			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String shippingAddress = request.getParameter("shippingAddress");
			String orderStateId = request.getParameter("orderState");

			OrderStateDAO stateDAO = new OrderStateDAOImp();
			OrderState orderState = stateDAO.getOrderState(Integer.parseInt(orderStateId));

			OrderDAO orderDAO = new OrderDAOImp();

			Order order = orderDAO.getOrder(Integer.parseInt(id));
			int lastOrderState = order.getOrderState().getId();

			order.setCustomerName(customerName);
			order.setPhoneNumber(phoneNumber);
			order.setShippingAddress(shippingAddress);
			order.setOrderState(orderState);

			// Ai là người sửa
			User user = (User) session.getAttribute("User");
			order.setFixer(user.getUsername());

			boolean success = orderDAO.updateOrder(order);
			if (success) {
				switch (orderState.getId()) {
				case 1:
					System.out.println("Trường hợp 1: Chờ xác nhận -> không làm gì cả...");
					break;
				case 2:
					System.out.println("Trường hợp 2: đã xác nhận!");
					if (lastOrderState != 2) {
						try {
							System.out.println("Trừ hàng trong kho đi...");
							OrderDetailDAO detailDAO = new OrderDetailDAOImp();
							List<OrderDetail> orderDetails = detailDAO.findOrderDetailsByOrder(order);

							ProductDAO productDAO = new ProductDAOImp();
							for (OrderDetail o : orderDetails) {

								Product p = o.getProduct();
								System.out.println("Lấy ra đối tượng product hiện tại...");

								int lastStock = p.getInStock();
								System.out.println("Lấy số hàng hiện tại có trong kho " + lastStock);

								int currentStock = lastStock - o.getQuantity();
								System.out.println("Tạo biến lưu trữ số lượng sau khi lấy từ kho ra với giá trị là "
										+ currentStock);

								p.setInStock(currentStock);
								System.out.println("Set lại giá trị vừa lấy...");

								productDAO.updateProduct(p);
								System.out.println("Thực thi vào trong csdl");

							}
							System.out.println("Trừ hàng trong kho đi thành công!");

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("Đã hoàn tất trường hợp 2 -> đã xác nhận!");
					break;
				case 3:
					System.out.println(
							"Trường hợp 3: đã hoàn tất, không làm gì cả...  --> sau này có thể cộng vào doanh thu");
					break;
				case 4:
					System.out.println("Trường hợp 4: hủy đơn hàng \n Trường hợp này là \n");

					if (lastOrderState == 2) {
						System.out.println("Đã xác nhận đơn hàng nhưng người dùng lại hủy -> trả hàng về kho");
						try {
							OrderDetailDAO detailDAO = new OrderDetailDAOImp();
							List<OrderDetail> orderDetails = detailDAO.findOrderDetailsByOrder(order);

							ProductDAO productDAO = new ProductDAOImp();
							for (OrderDetail o : orderDetails) {

								Product p = o.getProduct();
								System.out.println("Lấy ra đối tượng product hiện tại...");

								int lastStock = p.getInStock();
								System.out.println("Lấy số hàng hiện tại có trong kho " + lastStock);

								int currentStock = lastStock + o.getQuantity();
								System.out.println(
										"Tạo biến lưu trữ số lượng sau khi trả về kho với giá trị là " + currentStock);

								p.setInStock(currentStock);
								System.out.println("Set lại giá trị vừa lấy...");

								productDAO.updateProduct(p);
								System.out.println("Thực thi vào trong csdl");

							}
							System.out.println("Trả lại hàng vào kho thành công!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Chưa xác nhận đơn hàng nên không làm gì cả!");
					}
					System.out.println("Đã hoàn tất trường hợp 4!");
					break;
				default:
					break;
				}
				message = new Message(Message.SUCCESS, "Update thành công!");
			} else {
				message = new Message(Message.ERROR, "Update thất bại!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(Message.ERROR, e.getMessage());
		} finally {
			session.setAttribute("Message", message);
			response.sendRedirect("order.jsp");
		}
	}

	/**
	 * Xóa order
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			String sid = request.getParameter("orderId");
			int id = Integer.parseInt(sid);
			OrderDAO dao = new OrderDAOImp();
			Order o = dao.getOrder(id);
			boolean success = dao.deleteOrder(o);
			Message message = null;
			if (success) {
				message = new Message(Message.SUCCESS, "Xoá order thành công!");
				session.setAttribute("Message", message);
				response.sendRedirect("order.jsp");
			} else {
				message = new Message(Message.ERROR, "Xóa order thất bại!");
				session.setAttribute("Message", message);
				response.sendRedirect("order.jsp?action=update&orderId=" + sid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(404, "Server đang bảo trì!");
		}
	}

	private void updateOrderDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			Message message;
			String orderId = request.getParameter("orderId");
			String orderDetailId = request.getParameter("orderDetailId");
			String squantity = request.getParameter("quantity");

			int id = Integer.parseInt(orderDetailId);
			int quantity = Integer.parseInt(squantity);

			OrderDetailDAO dao = new OrderDetailDAOImp();

			OrderDetail od = dao.getOrderDetail(id);
			od.setQuantity(quantity);

			boolean succcess = dao.updateOrderDetail(od);
			if (succcess) {
				Order order = od.getOrder();
				List<OrderDetail> details = dao.findOrderDetailsByOrder(order);
				float amount = 0;
				for (OrderDetail x : details) {
					amount += x.getQuantity() * x.getProduct().getPrice();
				}
				OrderDAO orderDAO = new OrderDAOImp();
				order.setAmount(amount);
				if(orderDAO.updateOrder(od.getOrder())) {
					message = new Message(Message.SUCCESS, "Sửa số lượng thành công!");
					session.setAttribute("Message", message);
				}else {
					message = new Message(Message.ERROR, "Lỗi không thể sửa lại tổng số tiền trong order!");
					session.setAttribute("Message", message);
				}
				response.sendRedirect("order.jsp?action=update&orderId=" + orderId);
			} else {
				message = new Message(Message.ERROR, "Xóa thất bại!");
				session.setAttribute("Message", message);
				response.sendRedirect(
						"order.jsp?action=updateOrderDetail&orderId=" + orderId + "&orderDetailId=" + orderDetailId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(404, "Server đang bảo trì!");
		}
	}

	private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			Message message;
			String orderDetailId = request.getParameter("orderDetailId");
			String orderId = request.getParameter("orderId");
			int id = Integer.parseInt(orderDetailId);
			OrderDetailDAO dao = new OrderDetailDAOImp();
			OrderDetail od = dao.getOrderDetail(id);
			boolean succcess = dao.deleteOrderDetail(od);
			if (succcess) {
				message = new Message(Message.SUCCESS, "Xóa thành công!");
				session.setAttribute("Message", message);
				response.sendRedirect("order.jsp?action=update&orderId=" + orderId);
			} else {
				message = new Message(Message.ERROR, "Xóa thất bại!");
				session.setAttribute("Message", message);
				response.sendRedirect(
						"order.jsp?action=removeOrderDetail&orderId=" + orderId + "&orderDetailId=" + orderDetailId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(404, "Server đang bảo trì!");
		}
	}

}
