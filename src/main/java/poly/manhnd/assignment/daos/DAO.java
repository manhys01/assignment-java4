package poly.manhnd.assignment.daos;

import java.util.List;

public interface  DAO {

	public abstract boolean create(Object o) throws Exception;

	public abstract boolean update(Object o) throws Exception;

	public abstract boolean delete(Object o) throws Exception;

	public abstract List<?> getAllObjects(String hql) throws Exception;

	public abstract Object getObject(Class<?> c, int id) throws Exception;

	public abstract List<?> getObjects(String hql, int startPosition, int maxResult) throws Exception;

	public abstract long getTotalRecords(String object) throws Exception;

	public abstract Object getObjectByUniqueField(String objectClass, String uniqueField, String uniqueValue);

	public abstract Object getLatestObject(String hql) throws Exception;

}
