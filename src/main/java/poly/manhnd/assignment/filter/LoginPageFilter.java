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

/**
 * Servlet Filter implementation class LoginPageFilter
 */
@WebFilter("/login.jsp")
public class LoginPageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginPageFilter() {
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
		System.out.println("Login page filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("User");
		if(user==null) {
			System.out.println("Chưa đăng nhập... cho qua " );
			chain.doFilter(request, response);
		}else {
			System.out.println("Đăng nhập rồi");
			String url = req.getHeader("referer");
			if(url==null) {
				url = (String) session.getAttribute("recentPage");
			}
			if(url.contains("login.jsp")){
				url = "index.jsp";
			}
			System.out.println("trở lại trang " + url);
			resp.sendRedirect(url);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
