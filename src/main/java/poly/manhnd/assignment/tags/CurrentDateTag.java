package poly.manhnd.assignment.tags;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CurrentDateTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		Date date = new Date();
		DateFormat shortDate = DateFormat.getDateInstance(DateFormat.SHORT);
		String currentDateFormatted = shortDate.format(date);
		try {
			JspWriter out = pageContext.getOut();
			out.print(currentDateFormatted);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return SKIP_BODY;
	}
}
