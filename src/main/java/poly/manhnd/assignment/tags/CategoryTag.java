package poly.manhnd.assignment.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.CategoryDAO;
import poly.manhnd.assignment.daos.entities.implement.CategoryDAOImp;
import poly.manhnd.assignment.entities.Category;

public class CategoryTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			CategoryDAO dao = new CategoryDAOImp();
			List<Category> list = dao.getAllCategories();
			int total = dao.getTotalCategory();
			pageContext.setAttribute("categories", list);
			pageContext.setAttribute("totalCategory", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
