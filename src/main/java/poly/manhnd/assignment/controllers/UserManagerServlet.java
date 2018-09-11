package poly.manhnd.assignment.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;

/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserManagerServlet")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			HttpSession session = request.getSession();
			switch (action) {
			case "update user":
				try {
					Message message = null;
					UserDAO dao = new UserDAOImp();
					User user = dao.getUser(Integer.parseInt(request.getParameter("userId")));
					user.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
					user.setBlocked(Boolean.parseBoolean(request.getParameter("blocked")));

					String page = request.getParameter("page");
					page = page == null || page.isEmpty() ? "" : "?page=" + page;
					
					boolean success = dao.updateUser(user);
					if (success) {
						message = new Message(Message.SUCCESS, "Cập nhật user " + user.getUsername() + " thành công!");
						session.setAttribute("Message", message);
						response.sendRedirect("user.jsp" + page);
					} else {
						message = new Message(Message.ERROR, "Cập nhật user " + user.getUsername() + " thất bại!");
						session.setAttribute("Message", message);
						response.sendRedirect("user.jsp" + page);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new ServletException("Có lỗi đã xảy ra!");
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException("Server đang bảo trì!");
				}
				break;
			default:
				break;
			}
		}
	}

}
