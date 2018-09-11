package poly.manhnd.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.carts.Cart;
import poly.manhnd.assignment.carts.ProductDTO;
import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action != null) {
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("Cart");
			if (cart == null) {
				cart = new Cart();
			}
			switch (action) {
			case "Add To Cart":
				addToCart(request, response, session, cart);
				break;
			case "Update Product":
				updateProductInTheCart(request, response, session, cart);
				break;
			case "Remove Product":
				removeProductInTheCart(request, response, session, cart);
				break;
			default:
				break;
			}
		}
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart cart)
			throws IOException {
		try {
			String sId = request.getParameter("id");
			ProductDAO productDAO = new ProductDAOImp();
			Product product = productDAO.getProduct(Integer.parseInt(sId));
			ProductDTO p = new ProductDTO(product);
			cart.addToCart(p);
			session.setAttribute("Cart", cart);
			//total number of products int the cart
			int totalProducts = cart.size();
			session.setAttribute("totalProducts", totalProducts);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect("cart.jsp");
		}
	}

	private void updateProductInTheCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart cart)
			throws IOException {
		try {
			String sId = request.getParameter("id");
			String quantity = request.getParameter("quantity");
			System.out.println("Id = " + sId);
			System.out.println("quantity = " + quantity);
			cart.get(Integer.parseInt(sId)).setQuantity(Integer.parseInt(quantity));
			session.setAttribute("Cart", cart);
			int totalProducts = cart.size();
			session.setAttribute("totalProducts", totalProducts);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect("cart.jsp");
		}
	}

	private void removeProductInTheCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Cart cart)
			throws IOException {
		String sId = request.getParameter("id");
		cart.removeProduct(Integer.parseInt(sId));
		if (cart.isEmpty()) {
			response.sendRedirect("showproduct.jsp");
		} else {
			session.setAttribute("Cart", cart);
			response.sendRedirect("cart.jsp");
		}
		int totalProducts = cart.size();
		session.setAttribute("totalProducts", totalProducts);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
