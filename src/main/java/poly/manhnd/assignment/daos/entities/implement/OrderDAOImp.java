package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.User;
import poly.manhnd.assignment.enums.Sort;

public class OrderDAOImp extends DAOImp implements OrderDAO {

	@Override
	public boolean createOrder(Order o) throws Exception {
		return create(o);
	}

	@Override
	public boolean updateOrder(Order o) throws Exception {
		return update(o);
	}

	@Override
	public boolean deleteOrder(Order o) throws Exception {
		return delete(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders(Sort by) throws Exception {
		switch (by) {
		case DESC:
			return (List<Order>) getAllObjects("FROM Order o ORDER BY o.createAt DESC");
		default:
			return (List<Order>) getAllObjects("FROM Order o ORDER BY o.createAt ASC");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders(Sort by, String search) throws Exception {
		if (search == null || search.trim().isEmpty()) {
			switch (by) {
			case DESC:
				return (List<Order>) getAllObjects("FROM Order o ORDER BY o.createAt DESC");
			default:
				return (List<Order>) getAllObjects("FROM Order o ORDER BY o.createAt ASC");
			}
		} else {
			search = "'%" + search.trim() + "%'";
			switch (by) {
			case DESC:
				return (List<Order>) getAllObjects("FROM Order o "
						+ "WHERE o.customerName like " + search 
						+ " OR o.shippingAddress like " + search
						+ " OR o.phoneNumber like " + search
						+ " OR o.user.username like " + search
						+ " ORDER BY o.createAt DESC");
			default:
				return (List<Order>) getAllObjects("FROM Order o "
						+ "WHERE o.customerName like " + search 
						+ " OR o.shippingAddress like " + search
						+ " OR o.phoneNumber like " + search
						+ " OR o.user.username like " + search
						+ " ORDER BY o.createAt ASC");
			}
		}
	}

	@Override
	public Order getOrder(int id) throws Exception {
		return (Order) getObject(Order.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getListOrders(Sort by, int startPosition, int maxResult) throws Exception {
		switch (by) {
		case DESC:
			return (List<Order>) getObjects("FROM Order o ORDER BY o.createAt DESC", startPosition, maxResult);
		default:
			return (List<Order>) getObjects("FROM Order o ORDER BY o.createAt ASC", startPosition, maxResult);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getListOrders(Sort by, String search, int startPosition, int maxResult) throws Exception {
		if (search == null || search.trim().isEmpty()) {
			switch (by) {
			case DESC:
				return (List<Order>) getObjects("FROM Order o ORDER BY o.createAt DESC", startPosition, maxResult);
			default:
				return (List<Order>) getObjects("FROM Order o ORDER BY o.createAt ASC", startPosition, maxResult);
			}
		} else {
			search = "'%" + search.trim() + "%'";
			switch (by) {
			case DESC:
				return (List<Order>) getObjects("FROM Order o "
						+ "WHERE o.customerName like " + search 
						+ " OR o.shippingAddress like " + search
						+ " OR o.phoneNumber like " + search
						+ " OR o.user.username like " + search
						+ " ORDER BY o.createAt DESC",startPosition, maxResult);
			default:
				return (List<Order>) getObjects("FROM Order o "
						+ "WHERE o.customerName like " + search 
						+ " OR o.shippingAddress like " + search
						+ " OR o.phoneNumber like " + search
						+ " OR o.user.username like " + search
						+ " ORDER BY o.createAt ASC",startPosition, maxResult);
			}
		}
		
	}
	

	@Override
	public int getTotalOrder() throws Exception {
		return (int) getTotalRecords("Order");
	}

	@Override
	public int getTotalOrder(String search) throws Exception {
		return getOrders(Sort.ASC,search).size();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrders(User user) throws Exception {
		return (List<Order>) getAllObjects(String.format("FROM Order o WHERE o.user='%s'", user.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrders(Sort by, User user, int startPosition, int maxResult) throws Exception {
		switch (by) {
		case DESC:
			return (List<Order>) getObjects(
					String.format("FROM Order o WHERE o.user='%s' ORDER BY o.createAt DESC", user.getId()),
					startPosition, maxResult);
		default:
			return (List<Order>) getObjects(
					String.format("FROM Order o WHERE o.user='%s' ORDER BY o.createAt ASC", user.getId()),
					startPosition, maxResult);
		}
	}

	@Override
	public int getTotalWaitingOrder() throws Exception {
		return getAllObjects("FROM Order o WHERE o.orderState='" + 1 + "'").size();
	}

}
