package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;

public interface OrderDetailDAO {
	
	public boolean createOrderDetail(OrderDetail od) throws Exception;

	public boolean updateOrderDetail(OrderDetail od) throws Exception;

	public boolean deleteOrderDetail(OrderDetail od) throws Exception;

	public List<OrderDetail> getAllOrderDetails() throws Exception;
	
	public List<OrderDetail> findOrderDetailsByOrder(Order order) throws Exception;

	public OrderDetail getOrderDetail(int id) throws Exception;

	public List<OrderDetail> getListOrderDetails(int startPosition, int maxResult) throws Exception;

	public int getTotalOrderDetail() throws Exception;
}
