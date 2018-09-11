package poly.manhnd.assignment.filter;

import java.io.IOException;
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

import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.info.Message;

/**
 * Servlet Filter implementation class ProfileFilter
 */
@WebFilter("/profile.jsp")
public class ProfileFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ProfileFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("profile filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("User");
		if(user==null) {
			System.out.println("Chưa đăng nhập..");
			Message message = new Message(Message.ERROR,"Bạn phải đăng nhập trước");
			session.setAttribute("recentPage", "/assignment/profile.jsp");
			session.setAttribute("Message", message);
			resp.sendRedirect("login.jsp");
			return;
		}else {
			System.out.println("Đã đăng nhập... cho qua" );
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
