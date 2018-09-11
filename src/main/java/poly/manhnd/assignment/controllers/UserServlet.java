package poly.manhnd.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;
import poly.manhnd.assignment.utils.CookieUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet...");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		try {
			switch (action) {
			case "Change Password":
				changePassword(request, response, session, user);
				break;
			case "update fullname":
				updateFullName(response, session, user);
				break;
			case "update phone":
				updatePhone(response, session, user);
				break;
			case "update address":
				updateAddress(response, session, user);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Máy chủ đang bảo trì!");
		}
	}
	private void updateAddress(HttpServletResponse response, HttpSession session, User user)
			throws Exception, IOException {
		Message message;
		UserDAO u = new UserDAOImp();
		if (user != null) {
			boolean success = u.updateUser(user);
			if (success) {
				message = new Message(Message.SUCCESS, "Đổi địa chỉ thành công!");
				session.setAttribute("Message", message);
			}else {
				message = new Message(Message.SUCCESS, "Đổi địa chỉ thất bại!");
				session.setAttribute("Message", message);
			}
			response.sendRedirect("profile.jsp?edit=address");
		}else {
			response.sendError(404);
		}
	}

	private void updatePhone(HttpServletResponse response, HttpSession session, User user)
			throws Exception, IOException {
		Message message;
		UserDAO u = new UserDAOImp();
		if (user != null) {
			boolean success = u.updateUser(user);
			if (success) {
				message = new Message(Message.SUCCESS, "Đổi số điện thoại thành công!");
				session.setAttribute("Message", message);
			}else {
				message = new Message(Message.SUCCESS, "Đổi số điện thoại thất bại!");
				session.setAttribute("Message", message);
			}
			response.sendRedirect("profile.jsp?edit=phone");
		}else {
			response.sendError(404);
		}
	}

	private void updateFullName(HttpServletResponse response, HttpSession session, User user)
			throws Exception, IOException {
		Message message;
		UserDAO u = new UserDAOImp();
		if (user != null) {
			boolean success = u.updateUser(user);
			if (success) {
				message = new Message(Message.SUCCESS, "Đổi tên thành công!");
				session.setAttribute("Message", message);
			}else {
				message = new Message(Message.SUCCESS, "Đổi tên thất bại!");
				session.setAttribute("Message", message);
			}
			response.sendRedirect("profile.jsp?edit=fullname");
		}else {
			response.sendError(404);
		}
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			User user) throws Exception, IOException {
		Message message = null;
		System.out.println("Đổi mật khẩu...");
		UserDAO u = new UserDAOImp();
		if (user != null) {
			boolean success = u.updateUser(user);
			if (success) {
				message = new Message(Message.SUCCESS, "Đổi mật khẩu thành công!");
				session.setAttribute("Message", message);

				if (CookieUtil.getCookieValue(request.getCookies(), "username") != null) {
					Cookie usernameCookie = new Cookie("username", user.getUsername());
					usernameCookie.setMaxAge(60 * 60 * 24 * 365);
					Cookie passwordCookie = new Cookie("password", user.getPassword());
					passwordCookie.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
			} else {
				message = new Message(Message.ERROR, "Đổi mật khẩu thất bại!");
				session.setAttribute("Message", message);
			}
			response.sendRedirect("profile.jsp?change=password");
		} else {
			response.sendError(404);
		}
	}

}
