package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.OrderDetailDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.Order;
import poly.manhnd.assignment.entities.OrderDetail;

public class OrderDetailDAOImp extends DAOImp implements OrderDetailDAO {

	@Override
	public boolean createOrderDetail(OrderDetail od) throws Exception {
		return create(od);
	}

	@Override
	public boolean updateOrderDetail(OrderDetail od) throws Exception {
		return update(od);
	}

	@Override
	public boolean deleteOrderDetail(OrderDetail od) throws Exception {
		return delete(od);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> getAllOrderDetails() throws Exception {
		return (List<OrderDetail>) getAllObjects("From OrderDetail");
	}

	@Override
	public OrderDetail getOrderDetail(int id) throws Exception {
		return (OrderDetail) getObject(OrderDetail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> getListOrderDetails(int startPosition, int maxResult) throws Exception {
		return (List<OrderDetail>) getObjects("FROM OrderDetail", startPosition, maxResult);
	}

	@Override
	public int getTotalOrderDetail() throws Exception {
		return (int) getTotalRecords("OrderDetail");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> findOrderDetailsByOrder(Order order) throws Exception {
		return (List<OrderDetail>) getAllObjects(String.format("FROM OrderDetail o WHERE o.order='%s'", order.getId()));
	}

}
