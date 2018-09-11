package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.ProductDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.Product;

public class ProductDAOImp extends DAOImp implements ProductDAO {

	@Override
	public boolean createProduct(Product p) throws Exception {
		return create(p);
	}

	@Override
	public boolean updateProduct(Product p) throws Exception {
		return update(p);
	}

	@Override
	public boolean deleteProduct(Product p) throws Exception {
		return delete(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() throws Exception {
		return (List<Product>) getAllObjects("FROM Product");
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProducts(String search) throws Exception {
		String condition = null;
		if (search == null || search.equalsIgnoreCase("all")) {
			condition = "FROM Product";
		} else {
			search = "%" + search + "%";
			condition = String.format("FROM Product p WHERE p.name like '%s'", search);
		}
		return (List<Product>) getAllObjects(condition);
	}

	@Override
	public Product getProduct(int id) throws Exception {
		return (Product) getObject(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getListProducts(int startPosition, int maxResult) throws Exception {
		return (List<Product>) getObjects("FROM Product", startPosition, maxResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getListProducts(String keyword, int startPosition, int maxResult) throws Exception {
		String condition = null;
		if (keyword == null || keyword.trim().isEmpty()) {
			condition = "FROM Product";
		} else {
			keyword = "'%" + keyword + "%'";
			condition = String.format(
					"FROM Product p WHERE p.name like %s OR p.category.name like %s OR p.manufacturer.name like %s",
					keyword, keyword, keyword);
		}
		System.out.println("condition: " + condition);
		return (List<Product>) getObjects(condition, startPosition, maxResult);
	}

	@Override
	public int getTotalProduct() throws Exception {
		return (int) getTotalRecords("Product");
	}

	@Override
	public int getTotalProduct(String keyword) throws Exception {
		return findProducts(keyword).size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getListRamdomProducts(int limit) throws Exception {
		return (List<Product>) getObjects("FROM Product p Order by RAND()", 0, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsByManufacturer(int manufacturer) throws Exception {
		return (List<Product>) getAllObjects("FROM Product p Where p.manufacturer='" + manufacturer + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsByManufacturer(int manufacturer, int startPosition, int maxResult)
			throws Exception {
		return (List<Product>) getObjects("FROM Product p Where p.manufacturer='" + manufacturer + "'", startPosition,
				maxResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsByCategory(int category) throws Exception {
		return (List<Product>) getAllObjects("FROM Product p Where p.category='" + category + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsByCategory(int category, int startPosition, int maxResult) throws Exception {
		return (List<Product>) getObjects("FROM Product p Where p.category='" + category + "'", startPosition,
				maxResult);
	}

	@Override
	public Product getLatestProduct() throws Exception {
		return (Product) getLatestObject("FROM Product p ORDER BY p.createAt desc");
	}

}
