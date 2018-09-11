package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.OrderStateDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.OrderState;

public class OrderStateDAOImp extends DAOImp implements OrderStateDAO {

	@Override
	public boolean createOrderState(OrderState os) throws Exception {
		return create(os);
	}

	@Override
	public boolean updateOrderState(OrderState os) throws Exception {
		return update(os);
	}

	@Override
	public boolean deleteOrderState(OrderState os) throws Exception {
		return delete(os);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderState> getAllOrderStates() throws Exception {
		return (List<OrderState>) getAllObjects("FROM OrderState");
	}

	@Override
	public OrderState getOrderState(int id) throws Exception {
		return (OrderState) getObject(OrderState.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderState> getListOrderStates(int startPosition, int maxResult) throws Exception {
		return (List<OrderState>) getObjects("FROM OrderState", startPosition, maxResult);
	}

	@Override
	public int getTotalOrderState() throws Exception {
		return (int) getTotalRecords("OrderState");
	}

}
