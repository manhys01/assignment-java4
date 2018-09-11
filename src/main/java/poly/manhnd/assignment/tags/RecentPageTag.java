package poly.manhnd.assignment.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RecentPageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String currentPage;

	@Override
	public int doStartTag() throws JspException {
		if (currentPage != null) {
			pageContext.getSession().setAttribute("recentPage", currentPage);
		}
		return SKIP_BODY;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

}
