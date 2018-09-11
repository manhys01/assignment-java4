package poly.manhnd.assignment.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		System.out.println("Tìm kiếm....");
		String keyword = request.getParameter("search");
		System.out.println("keyword = " + keyword);
		HttpSession session = request.getSession();
		if(keyword==null || keyword.isEmpty()) {
			keyword = "all";
		}
		session.setAttribute("Keyword", keyword);
		System.out.println("Keyword = " + keyword);
		response.sendRedirect("showproduct.jsp?search="+keyword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
