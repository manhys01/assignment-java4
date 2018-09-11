package poly.manhnd.assignment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;

@WebFilter(urlPatterns = { "/UserServlet" })
public class UserChangeInfoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("User change info filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("User");
		
		req.setCharacterEncoding("utf-8");
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendError(404);
		} else {
			String action = request.getParameter("action");
			
			System.out.println("action = " + action);
			if (action != null) {
				Message message = null;
				switch (action) {
				case "Change Password":
					String password = request.getParameter("password");
					String new_password = request.getParameter("new_password");
					String confirm_new_password = request.getParameter("confirm_new_password");
					
					if (password == null || password.trim().isEmpty()) {
						message = new Message(Message.ERROR, "Vui lòng nhập mật khẩu cũ!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?change=password");
						return;
					}
					if (new_password == null || new_password.trim().isEmpty()) {
						message = new Message(Message.ERROR, "Vui lòng nhập mật khẩu mới!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?change=password");
						return;
					}
					if (confirm_new_password == null || confirm_new_password.trim().isEmpty()) {
						message = new Message(Message.ERROR, "Vui lòng xác nhận lại mật khẩu!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?change=password");
						return;
					}
					if (!new_password.equals(confirm_new_password)) {
						message = new Message(Message.ERROR, "Mật khẩu mới và xác nhận không trùng nhau!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?change=password");
						return;
					}

					
					if (!user.getPassword().equals(password)) {
						message = new Message(Message.ERROR,
								"Mật khẩu cũ bạn nhập không trùng với mật khẩu hiện tại của bạn!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?change=password");
						return;
					}
					user.setPassword(new_password);
					break;
				case "update fullname":
					System.out.println("update fullname");
					
					String fullname = request.getParameter("fullname");
					if(fullname==null|| fullname.trim().isEmpty()) {
						message = new Message(Message.ERROR,
								"Vui lòng nhập vào họ và tên mới của bạn!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?edit=fullname");
						return;
					}
					
					//update
					user.setFullname(fullname);
					break;
				case "update phone":
					System.out.println("update phone");
					
					String phone = request.getParameter("phone");
					if(phone==null|| phone.trim().isEmpty()) {
						message = new Message(Message.ERROR,
								"Vui lòng nhập vào số điện thoại mới của bạn!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?edit=phone");
						return;
					}
					
					//update
					user.setPhone(phone);
					break;
				case "update address":
					System.out.println("update address");
					
					String address = request.getParameter("address");
					if(address==null|| address.trim().isEmpty()) {
						message = new Message(Message.ERROR,
								"Vui lòng nhập vào địa mới mới của bạn!");
						session.setAttribute("Message", message);
						resp.sendRedirect("profile.jsp?edit=address");
						return;
					}
					
					//update
					user.setAddress(address);
					break;
				default:
					System.out.println("default user action");
					resp.sendError(404);
					return;
				}
			}
			chain.doFilter(request, response);
		}
	}

}
