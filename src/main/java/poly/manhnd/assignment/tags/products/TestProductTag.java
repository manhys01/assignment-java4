package poly.manhnd.assignment.tags.products;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class TestProductTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int maxResult;
	private int page;
	private int startPosition;

	@Override
	public int doStartTag() throws JspException {
		try {
			String spage = pageContext.getRequest().getParameter("page");
			String param = pageContext.getRequest().getParameter("search");
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage);
				startPosition = page - 1;
				startPosition = startPosition * maxResult + 1;
			}
			System.out.println("request: " + spage);

			// Tìm kiếm hiển thị sản phẩm
			ProductDAO dao = new ProductDAOImp();
			if(param==null) {
				param = "";
			}else {
				param = param.trim();
			}
			int total = dao.getTotalProduct(param);
			List<Product> products = dao.getListProducts(param, startPosition, maxResult);

			pageContext.setAttribute("totalRecords", total);
			if (products == null || products.isEmpty()) {
				System.out.println("Không tìm thấy..");
				pageContext.getOut().print(
						"<div class='col-md-12'><h3>Không tìm thấy sản phẩm nào có tên là " + param + "</h3></div>");
			} else {

				int totalResult = products.size();
				int max = maxResult / 2;
				int totalGroup = (int) Math.ceil(totalResult * 1.0 / max);

				int[] indexes = new int[totalGroup];
				for (int i = 0; i < indexes.length; i++) {
					indexes[i] = i * max;
				}

				LinkedHashMap<Integer, List<Product>> rows = new LinkedHashMap<>();

				int row = 0;
				for (int k = 0; k < indexes.length; k++) {
					List<Product> sublist = new ArrayList<>();
					row++;
					int firstIndexOfGroups = indexes[k];
					int condition = max + firstIndexOfGroups;
					for (int i = firstIndexOfGroups; i < condition; i++) {
						try {
							sublist.add(products.get(i));
						} catch (Exception e) {
							break;
						}
					}
					rows.put(row, sublist);
				}

				pageContext.setAttribute("rows", rows);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return SKIP_BODY;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
}
