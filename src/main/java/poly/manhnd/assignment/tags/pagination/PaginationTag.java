package poly.manhnd.assignment.tags.pagination;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.utils.PaginationUtil;

public class PaginationTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private int page;
	private int totalRecords;
	private String uri;
	private int max;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if(totalRecords==0) {
				return SKIP_BODY;
			}
			if (page == 0) {
				page = 1;
			}
			// Lấy ra từ khóa search
			String search = pageContext.getRequest().getParameter("search");
			String category = pageContext.getRequest().getParameter("category");
			String manufacturer = pageContext.getRequest().getParameter("manufacturer");
			String view = pageContext.getRequest().getParameter("view");
			if (search != null) {
				if (search.equalsIgnoreCase("all")) {
					// tìm mặc định
					uri = uri + "?search=all";
				} else {
					if (search != null || !search.isEmpty()) {
						// tìm theo từ khóa
						uri = uri + "?search=" + search;
					}
				}
			}else if(manufacturer!=null){
				uri = uri + "?manufacturer=" + manufacturer;
			}else if(category!=null) {
				uri = uri + "?category=" + category;
			}else if(view!=null) {
				uri = uri + "?view=" + view;
			}
			
			int total = (int) Math.ceil(totalRecords * 1.0 / max);
			out.print(PaginationUtil.createPagination(total, page, uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
