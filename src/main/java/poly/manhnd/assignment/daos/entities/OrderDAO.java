package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.enums.Sort;

public interface OrderDAO {

	public boolean createOrder(Order o) throws Exception;

	public boolean updateOrder(Order o) throws Exception;

	public boolean deleteOrder(Order o) throws Exception;

	public List<Order> getAllOrders(Sort by) throws Exception;

	public List<Order> getOrders(Sort by, String search) throws Exception;
	
	public Order getOrder(int id) throws Exception;

	public List<Order> getListOrders(Sort by, int startPosition, int maxResult) throws Exception;
	
	public List<Order> getListOrders(Sort by,String search, int startPosition, int maxResult) throws Exception;

	public int getTotalOrder() throws Exception;
	
	public int getTotalOrder(String search) throws Exception;

	public int getTotalWaitingOrder() throws Exception;

	public List<Order> findOrders(User user) throws Exception;

	public List<Order> findOrders(Sort by, User user, int startPosition, int maxResult) throws Exception;
}
