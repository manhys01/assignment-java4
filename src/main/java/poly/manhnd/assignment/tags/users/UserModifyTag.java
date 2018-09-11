package poly.manhnd.assignment.tags.users;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.entities.implement.UserDAOImp;
import poly.manhnd.assignment.entities.User;

public class UserModifyTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		try {
			String sUserId = pageContext.getRequest().getParameter("userId");
			int id = Integer.parseInt(sUserId);
			UserDAO userDAO = new UserDAOImp();
			User user = userDAO.getUser(id);
			if(user==null) {
				throw new Exception("Có lỗi xảy ra!");
			}
			pageContext.setAttribute("u", user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

}
