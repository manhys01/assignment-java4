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
import poly.manhnd.assignment.utils.MailUtilGmail;
import poly.manhnd.assignment.utils.SessionUtil;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("Register servlet");
			HttpSession session = request.getSession();
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			User user = new User(username, password, fullname, phone, address, email);
			UserDAO dao = new UserDAOImp();
			boolean success = dao.createUser(user);
			Message message = null;
			if (success) {
				message = new Message(Message.SUCCESS,
						"Đăng ký tài khoản thành công! Click <a href='login.jsp'>vào đây</a> để tới trang đăng nhập!");
				SessionUtil.removeSessionAttribute(session, "fullname", "username", "email", "phone", "address");
				session.setAttribute("Message", message);
				try {
					System.out.println("Gửi mail...");
					String to = email;
					String from = "manhnd2261995@gmail.com";
					String subject = "Đăng ký tài khoản thành công";
					String body = "Chào " + fullname + "\n" + "Bạn đã đăng ký tài khoản thành công\n" + "Tài khoản: "
							+ username + "\nPassword: " + password;
					boolean bodyIsHTML = false;
					MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
				
			} else {
				message = new Message(Message.ERROR, "Không thể kết nối tới server!");
				session.setAttribute("Message", message);
			}
			response.sendRedirect("register.jsp");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new ServletException("Server đang bảo trì!");
		}
	}

}
