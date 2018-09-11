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

import poly.manhnd.assignment.utils.HibernateUtil;

/**
 * Servlet Filter implementation class LoginServletFilter
 */
@WebFilter(urlPatterns= {"/LoginServlet"})
public class LoginServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("destroy...");
		if(HibernateUtil.getFactory()!=null) {
			HibernateUtil.getFactory().close();
			System.out.println("Close session factory...");
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Login servlet filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(!req.getMethod().equalsIgnoreCase("GET")) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(404);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
