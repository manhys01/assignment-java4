package poly.manhnd.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getHeader("referer");
		System.out.println("URL Login: " + url);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		HttpSession session = request.getSession();
		String recentPage = (String) session.getAttribute("recentPage");
		System.out.println("login -> recent page = " + recentPage);
		
		try {
			User user = new UserDAOImp().checkUserLogin(username, password);
			if (user == null) {
				Message message = new Message(Message.ERROR, "Sai username hoặc password!");
				session.setAttribute("Message", message);
				response.sendRedirect("login.jsp");
			} else {
				if (user.getPassword().equals(password)) {
					if (user.isBlocked()) {
						user = null;
						throw new Exception("Tài khoản này đã bị khóa!");
					} else {
						System.out.println("Đăng nhập thành công!");
						session.setAttribute("User", user);
						if (remember != null) {
							System.out.println("Thêm cookie");
							Cookie usernameCookie = new Cookie("username", username);
							usernameCookie.setMaxAge(60 * 60 * 24 * 365);
							Cookie passwordCookie = new Cookie("password", password);
							passwordCookie.setMaxAge(60 * 60 * 24 * 365);
							response.addCookie(usernameCookie);
							response.addCookie(passwordCookie);
						} else {
							System.out.println("Xóa cookie");
							for (Cookie c : request.getCookies()) {
								if (c.getName().equals("username") || c.getName().equals("password")) {
									c.setMaxAge(0);
									response.addCookie(c);
								}
							}
						}
						if (recentPage != null) {
							response.sendRedirect(recentPage);
						} else {
							response.sendRedirect(url);
						}
					}
				} else {
					throw new Exception("Sai username hoặc password!");
				}
			}
		} catch (Exception e) {
			session.setAttribute("username", request.getParameter("username"));
			Message message = new Message(Message.ERROR, e.getMessage());
			session.setAttribute("Message", message);
			response.sendRedirect("login.jsp");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
