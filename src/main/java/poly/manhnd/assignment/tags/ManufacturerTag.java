package poly.manhnd.assignment.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import poly.manhnd.assignment.daos.entities.ManufacturerDAO;
import poly.manhnd.assignment.daos.entities.implement.ManufacturerDAOImp;
import poly.manhnd.assignment.entities.Manufacturer;

public class ManufacturerTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			ManufacturerDAO dao = new ManufacturerDAOImp();
			List<Manufacturer> list = dao.getAllManufacturers();
			int total = dao.getTotalManufacturer();
			pageContext.setAttribute("manufacturers", list);
			pageContext.setAttribute("totalManufacturer", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
