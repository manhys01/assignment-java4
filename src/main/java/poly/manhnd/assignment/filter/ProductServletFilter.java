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

@WebFilter(urlPatterns = { "/ProductManagerServlet","/OrderManagerServlet" })
public class ProductServletFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ProductServletFilter, OrderManagerServlet filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendError(404);
		} else {
			System.out.println("Method: " + req.getMethod());
			chain.doFilter(request, response);
		}
	}

}
