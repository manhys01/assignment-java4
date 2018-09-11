package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.CategoryDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.Category;

public class CategoryDAOImp extends DAOImp implements CategoryDAO {

	@Override
	public boolean createCategory(Category c) throws Exception {
		return create(c);
	}

	@Override
	public boolean updateCategory(Category c) throws Exception {
		return update(c);
	}

	@Override
	public boolean deleteCategory(Category c) throws Exception {
		return delete(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() throws Exception {
		return (List<Category>) getAllObjects("FROM Category");
	}

	@Override
	public Category getCategory(int id) throws Exception {
		return (Category) getObject(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getListCategories(int startPosition, int maxResult) throws Exception {
		return (List<Category>) getObjects("FROM Category", startPosition, maxResult);
	}

	@Override
	public int getTotalCategory() throws Exception {
		return (int) getTotalRecords("Category");
	}

}
