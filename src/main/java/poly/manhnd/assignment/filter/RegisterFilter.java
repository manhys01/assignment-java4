package poly.manhnd.assignment.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.utils.ManhUtils;

/**
 * Servlet Filter implementation class RegisterFilter
 */
@WebFilter("/RegisterServlet")
public class RegisterFilter implements Filter {

	public RegisterFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter request register servlet");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		if (!req.getMethod().equalsIgnoreCase("GET")) {

			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String re_password = request.getParameter("re-password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			session.setAttribute("fullname", fullname);
			session.setAttribute("username", username);
			session.setAttribute("email", email);
			session.setAttribute("phone", phone);
			session.setAttribute("address", address);

			if (fullname == null || fullname.isEmpty()) {
				session.setAttribute("error_fullname", "Bạn chưa nhập họ tên!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (username == null || username.isEmpty()) {
				session.setAttribute("error_username", "Bạn chưa nhập tài khoản!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (username.length() < 6) {
				session.setAttribute("error_username", "Tài khoản tối thiểu phải là 6 ký tự!");
				resp.sendRedirect("register.jsp");
				return;
			}
			
			if (username.length() > 32) {
				session.setAttribute("error_username", "Tài khoản tối đa phải nhỏ hơn hoặc bằng 32 ký tự!");
				resp.sendRedirect("register.jsp");
				return;
			}
			
			if(username.trim().equalsIgnoreCase("default") || username.trim().equalsIgnoreCase("admin")) {
				session.setAttribute("error_username", "Tài khoản không hợp lệ!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (!ManhUtils.isUserName(username)) {
				session.setAttribute("error_username", "Tài khoản không hợp lệ!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (password == null || password.isEmpty()) {
				session.setAttribute("error_password", "Bạn chưa mật khẩu!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (re_password == null || re_password.isEmpty()) {
				session.setAttribute("error_re_password", "Vui lòng xác nhận lại mật khẩu bạn vừa nhập!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (!password.equals(re_password)) {
				session.setAttribute("error_password", "Mật khẩu và xác nhận không giống nhau!");
				session.setAttribute("error_re_password", "Mật khẩu và xác nhận không giống nhau!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (email == null || email.isEmpty()) {
				session.setAttribute("error_email", "Bạn chưa nhập email!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (!ManhUtils.isEmail(email)) {
				session.setAttribute("error_email", "Email không hợp lệ!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (phone == null || phone.isEmpty()) {
				session.setAttribute("error_phone", "Bạn chưa nhập số điện thoại!");
				resp.sendRedirect("register.jsp");
				return;
			}
			
			if (!ManhUtils.isPhoneNumber(phone)) {
				session.setAttribute("error_phone", "Số điện thoại không hợp lệ!");
				resp.sendRedirect("register.jsp");
				return;
			}

			if (address == null || address.isEmpty()) {
				session.setAttribute("error_address", "Bạn chưa nhập địa chỉ!");
				resp.sendRedirect("register.jsp");
				return;
			}

			UserDAO dao = new UserDAOImp();
			try {
				List<User> users = dao.getAllUsers();
				for (User u : users) {
					if (u.getUsername().equalsIgnoreCase(username)) {
						session.setAttribute("error_username", "Tài khoản này có người sử dụng!");
						resp.sendRedirect("register.jsp");
						return;
					}
				}

				for (User u : users) {
					if (u.getEmail().equalsIgnoreCase(email)) {
						session.setAttribute("error_email", "Email này đã tồn tại!");
						resp.sendRedirect("register.jsp");
						return;
					}
				}

			} catch (Exception e) {
				System.out.println("Lỗi filter");
				e.printStackTrace();
				throw new ServletException("Server đang bảo trì!");
			}

			chain.doFilter(request, response);
		} else {
			resp.sendError(404);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
