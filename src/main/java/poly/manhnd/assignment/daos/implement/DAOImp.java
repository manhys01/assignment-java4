package poly.manhnd.assignment.daos.implement;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import poly.manhnd.assignment.daos.DAO;
import poly.manhnd.assignment.utils.HibernateUtil;

public class DAOImp implements DAO {

	@Override
	public boolean create(Object o) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace(System.out);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	@Override
	public boolean update(Object o) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(o);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace(System.out);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Object o) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(o);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace(System.out);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	@Override
	public List<?> getAllObjects(String hql) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> list = session.createQuery(hql).getResultList();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return null;
	}

	@Override
	public Object getObject(Class<?> c, int id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Object obj = session.get(c, id);
			tx.commit();
			return obj;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return null;
	}

	@Override
	public List<?> getObjects(String hql, int startPosition, int maxResult) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setFirstResult(startPosition - 1);
			query.setMaxResults(maxResult);
			List<?> list = query.getResultList();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}

	@Override
	public long getTotalRecords(String obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("SELECT COUNT(o) FROM " + obj +" o");
			Long count = (Long) query.getSingleResult();
			tx.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return 0;
	}

	@Override
	public Object getObjectByUniqueField(String objectClass, String uniqueField, String uniqueValue) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery(String.format("FROM %s o where o.%s='%s'", objectClass, uniqueField, uniqueValue));
			Object o = query.getSingleResult();
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return null;
	}

	@Override
	public Object getLatestObject(String hql) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			Object o = query.getSingleResult();
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} 
		return null;
	}

}
