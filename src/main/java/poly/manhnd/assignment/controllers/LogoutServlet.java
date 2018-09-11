package poly.manhnd.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.manhnd.assignment.entities.User;

@WebServlet(urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-------Log out servlet-------");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		String recentPage = (String) session.getAttribute("recentPage");
		if (user != null) {
			/* session.removeAttribute("User"); */
			if (!user.isAdmin()) {
				session.invalidate();
				String url = request.getHeader("referer");

				if (recentPage != null) {
					System.out.println("Chuyển hướng tới trang vừa rồi " + recentPage);
					response.sendRedirect(recentPage);
				} else if (url != null) {
					if(url.contains("profile.jsp")) {
						url = "index.jsp";
					}
					System.out.println("Chuyển hướng tới trang url " + url);
					response.sendRedirect(url);
				} else {
					System.out.println("Send error");
					response.sendError(404);
				}
			} else {
				session.invalidate();
				System.out.println("admin logout => chuyển hướng tới trang login");
				response.sendRedirect("login.jsp");
			}
		} else {
			response.sendError(404);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
