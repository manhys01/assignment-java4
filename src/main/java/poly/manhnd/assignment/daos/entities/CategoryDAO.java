package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.Category;

public interface CategoryDAO {

	public boolean createCategory(Category c) throws Exception;

	public boolean updateCategory(Category c) throws Exception;

	public boolean deleteCategory(Category c) throws Exception;

	public List<Category> getAllCategories() throws Exception;

	public Category getCategory(int id) throws Exception;

	public List<Category> getListCategories(int startPosition, int maxResult) throws Exception;

	public int getTotalCategory() throws Exception;
	
}
