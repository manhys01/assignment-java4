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

import poly.manhnd.assignment.carts.Cart;

/**
 * Servlet Filter implementation class CartFiler
 */
@WebFilter(urlPatterns= {"/cart.jsp","/book.jsp"})
public class CartFiler implements Filter {

    /**
     * Default constructor. 
     */
    public CartFiler() {
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
		System.out.println("filter giỏ hàng...");
		HttpSession session = ((HttpServletRequest) request).getSession();
		Cart cart = (Cart) session.getAttribute("Cart");
		if(cart == null || cart.isEmpty()) {
			((HttpServletResponse)response).sendRedirect("showproduct.jsp");
		}else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
