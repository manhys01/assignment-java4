package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.Product;

public interface ProductDAO {

	public boolean createProduct(Product p) throws Exception;

	public boolean updateProduct(Product p) throws Exception;

	public boolean deleteProduct(Product p) throws Exception;

	public List<Product> getAllProducts() throws Exception;
	
	public List<Product> findProducts(String search) throws Exception;
	
	public List<Product> findProductsByManufacturer(int manufacturer) throws Exception;
	
	public List<Product> findProductsByManufacturer(int manufacturer,int startPosition, int maxResult) throws Exception;
	
	public List<Product> findProductsByCategory(int category) throws Exception;
	
	public List<Product> findProductsByCategory(int category,int startPosition, int maxResult) throws Exception;

	public Product getProduct(int id) throws Exception;

	public List<Product> getListProducts(int startPosition, int maxResult) throws Exception;

	public List<Product> getListRamdomProducts(int limit) throws Exception;
	
	public List<Product> getListProducts(String keyword, int startPosition, int maxResult) throws Exception;

	public int getTotalProduct() throws Exception;
	
	public int getTotalProduct(String keyword)  throws Exception ;
	
	public Product getLatestProduct() throws Exception;
}
