package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.OrderState;

public interface OrderStateDAO {
	
	public boolean createOrderState(OrderState os) throws Exception;

	public boolean updateOrderState(OrderState os) throws Exception;

	public boolean deleteOrderState(OrderState os) throws Exception;

	public List<OrderState> getAllOrderStates() throws Exception;

	public OrderState getOrderState(int id) throws Exception;

	public List<OrderState> getListOrderStates(int startPosition, int maxResult) throws Exception;

	public int getTotalOrderState() throws Exception;
}
