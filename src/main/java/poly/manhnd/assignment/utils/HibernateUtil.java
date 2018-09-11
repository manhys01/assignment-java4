package poly.manhnd.assignment.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory = null;

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	
	public static SessionFactory getFactory() {
		return factory;
	}
}
