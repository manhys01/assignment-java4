package poly.manhnd.assignment.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.CategoryDAOImp;
import poly.manhnd.assignment.daos.entities.implement.ManufacturerDAOImp;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Category;
import poly.manhnd.assignment.entities.Manufacturer;
import poly.manhnd.assignment.entities.Product;
import poly.manhnd.assignment.info.Message;
import poly.manhnd.assignment.utils.StringUtils;

@WebServlet("/ProductManagerServlet")
@MultipartConfig
public class ProductManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProductManagerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action != null) {
			HttpSession session = request.getSession();
			switch (action) {
			case "Create":
				createProduct(request, response, session);
				break;
			case "Update":
				updateProduct(request, response, session);
				break;
			case "Delete":
				deleteProduct(request, response, session);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException {
		Message message = null;
		try {
			String categoryId = request.getParameter("categoryId");
			String manufacturerId = request.getParameter("manufacturerId");
			String name = request.getParameter("ProductName");
			float price = Float.parseFloat(request.getParameter("ProductPrice"));
			int inStock = Integer.parseInt(request.getParameter("ProductInStock"));
			String description = request.getParameter("description");
			boolean discontinued = Boolean.parseBoolean(request.getParameter("discontinued"));
			String image = getImage(request, name);
			Category category = new CategoryDAOImp().getCategory(Integer.parseInt(categoryId));
			Manufacturer manufacturer = new ManufacturerDAOImp().getManufacturer(Integer.parseInt(manufacturerId));

			ProductDAO dao = new ProductDAOImp();

			// Tạo mới
			Product p = new Product(category, manufacturer, name, price, image, inStock, discontinued, description);

			boolean success = dao.createProduct(p);
			if (success) {
				System.out.println("thanh cong");
				message = new Message(Message.SUCCESS, "Thêm mới thành công!");
			} else {
				System.out.println("that bai");
				message = new Message(Message.ERROR, "Thêm mới thất bại!");
			}
			session.setAttribute("Message", message);
			String page = request.getParameter("page");
			page = page == null || page.isEmpty() ? "" : "?page=" + page;
			response.sendRedirect("product.jsp" + page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException {
		Message message = null;
		try {
			System.out.println("Update product...");
			int id = Integer.parseInt(request.getParameter("id"));
			String categoryId = request.getParameter("categoryId");
			String manufacturerId = request.getParameter("manufacturerId");
			String name = request.getParameter("ProductName");
			float price = Float.parseFloat(request.getParameter("ProductPrice"));
			int inStock = Integer.parseInt(request.getParameter("ProductInStock"));
			String description = request.getParameter("description");
			boolean discontinued = Boolean.parseBoolean(request.getParameter("discontinued"));
			String image = getImage(request, name);
			Category category = new CategoryDAOImp().getCategory(Integer.parseInt(categoryId));
			Manufacturer manufacturer = new ManufacturerDAOImp().getManufacturer(Integer.parseInt(manufacturerId));

			ProductDAO dao = new ProductDAOImp();

			// Sửa
			Product p = dao.getProduct(id);
			p.setCategory(category);
			p.setManufacturer(manufacturer);
			p.setName(name);
			p.setPrice(price);
			p.setInStock(inStock);
			p.setDescription(description);
			p.setDiscontinued(discontinued);
			if (image != null) {
				p.setImage(image);
			}

			boolean success = dao.updateProduct(p);
			if (success) {
				System.out.println("thanh cong");
				message = new Message(Message.SUCCESS, "Sửa thành công!");
			} else {
				System.out.println("that bai");
				message = new Message(Message.ERROR, "Sửa thất bại!");
			}
			session.setAttribute("Message", message);
			System.out.println("trả về trang product.jsp");
			String page = request.getParameter("page");
			page = page == null || page.isEmpty() ? "" : "?page=" + page;
			response.sendRedirect("product.jsp" + page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException {
		System.out.println("Xóa sản phẩm");
		String queryString = request.getQueryString();
		ProductDAO dao = new ProductDAOImp();
		Message message = null;
		try {
			Product p = dao.getProduct(Integer.parseInt(request.getParameter("id")));
			boolean success = dao.deleteProduct(p);
			if (success) {
				System.out.println("thanh cong");
				message = new Message(Message.SUCCESS, "Xóa thành công!");
				session.setAttribute("Message", message);
				String page = request.getParameter("page");
				page = page == null || page.isEmpty() ? "" : "?page=" + page;
				response.sendRedirect("product.jsp" + page);
			} else {
				System.out.println("that bai");
				message = new Message(Message.ERROR, "Xóa sản phẩm thất bại! <br>"
						+ "Có thể sản phẩm này hiện đang có trong hóa đơn nào đó nên không thể xóa được!");
				session.setAttribute("Message", message);
				response.sendRedirect("product.jsp?" + queryString);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ServletException("Lỗi id sản phẩm");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(
					e.getMessage() + "<br>" + "Vui lòng liên hệ với đơn vị sản xuất để được hỗ trợ khắc phục");
		}
	}

	private String getImage(HttpServletRequest request, String productName) throws IOException, ServletException {
		Part part = request.getPart("file-upload");

		String submittedFileName = part.getSubmittedFileName();

		if (submittedFileName == null || submittedFileName.isEmpty()) {
			System.out.println("Không sửa ảnh");
			return null;
		}

		String name = productName.trim();
		String ext = StringUtils.getFileExtension(submittedFileName);

		String filename = StringUtils.renameFile(name + ext);

		String saveInServer = getServletContext().getRealPath("/images/" + filename);

		part.write(saveInServer);
		System.out.println("Ghi file lên server thành công!\nĐường dẫn: " + saveInServer);

		/*try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println("Ngủ tí..");
			e.printStackTrace();
		}
*/
		String image = "images/" + filename;

		File file = new File(saveInServer);
		if (file.exists()) {
			// lấy đường dẫn từ file cấu hình
			String sourceFolder = getServletContext().getInitParameter("imageFolder");
			String projectPath = sourceFolder + "/";
			File saveInProject = new File(projectPath);
			if (saveInProject.isDirectory()) {
				Path source = file.toPath();
				File newFile = new File(projectPath + filename);
				Path target = newFile.toPath();
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Copy vào project thành công!");
				System.out.println("Đường dẫn: " + newFile.getAbsolutePath());
			} else {
				System.out.println(
						"Hiện tại chỉ lưu ảnh trên server...\nCần cấu hình lại context-param trong file web.xml để lưu ảnh vào project folder!");
			}
		}

		return image;
	}

}
