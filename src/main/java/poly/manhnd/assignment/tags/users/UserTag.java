package poly.manhnd.assignment.tags.users;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;

public class UserTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int maxResult;
	private int startPosition;

	@Override
	public int doStartTag() throws JspException {
		try {
			String spage = pageContext.getRequest().getParameter("page");
			String search = pageContext.getRequest().getParameter("search");
			int page;
			if (spage == null || spage.isEmpty()) {
				page = 1;
				startPosition = page;
			} else {
				page = Integer.parseInt(spage) - 1;
				startPosition = page * maxResult + 1;
			}

			UserDAO dao = new UserDAOImp();
			List<User> users = null;
			if(search==null) {
				search = "";
			}
			users = dao.getListUsers(search, startPosition, maxResult);

			int totalRecords = dao.getAllUsers(search).size();

			pageContext.setAttribute("users", users);
			pageContext.setAttribute("totalRecords", totalRecords);

		} catch (Exception e) {
			e.printStackTrace();
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
