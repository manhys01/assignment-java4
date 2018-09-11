package poly.manhnd.assignment.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class CookieSessionTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
}
