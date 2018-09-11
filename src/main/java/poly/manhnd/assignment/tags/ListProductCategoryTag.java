package poly.manhnd.assignment.tags;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.entities.implement.ProductDAOImp;
import poly.manhnd.assignment.entities.Product;

public class ListProductCategoryTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int categoryId;

	private int maxResult;

	@Override
	public int doStartTag() throws JspException {
		System.out.println("Tag show product by category...");
		ProductDAO dao = new ProductDAOImp();
		try {
			String spage = pageContext.getRequest().getParameter("page");
			int page;
			int startPosition;
			
			int totalRecords = dao.findProductsByCategory(categoryId).size();
			pageContext.setAttribute("sizeProducts", totalRecords);
			pageContext.setAttribute("totalRecords", totalRecords);
			
			if (maxResult == 0) {
				maxResult = totalRecords;
			}
			
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage);
				startPosition = page - 1;
				startPosition = startPosition * maxResult + 1;
			}
			
			// List product
			List<Product> products = dao.findProductsByCategory(categoryId, startPosition, maxResult);
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
