package assignment;

import poly.manhnd.assignment.daos.entities.OrderDAO;
import poly.manhnd.assignment.daos.entities.implement.OrderDAOImp;

public class Test2 {
	public static void main(String[] args) {
		try {
			OrderDAO dao = new OrderDAOImp();
			int totalOrderWaiting = dao.getTotalWaitingOrder();
			System.out.println("Tổng số order: " + totalOrderWaiting);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
