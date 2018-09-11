package poly.manhnd.assignment.daos.entities.implement;

import java.util.List;

import poly.manhnd.assignment.daos.entities.UserDAO;
import poly.manhnd.assignment.daos.implement.DAOImp;
import poly.manhnd.assignment.entities.User;

public class UserDAOImp extends DAOImp implements UserDAO {

	@Override
	public boolean createUser(User u) throws Exception {
		return create(u);
	}

	@Override
	public boolean updateUser(User u) throws Exception {
		return update(u);
	}

	@Override
	public boolean deleteUser(User u) throws Exception {
		return delete(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws Exception {
		return (List<User>) getAllObjects("FROM User");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(String param) throws Exception {
		return (List<User>) getAllObjects("FROM User u WHERE u.username like '%" + param + "%'");
	}

	@Override
	public User getUser(int id) throws Exception {
		return (User) getObject(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListUsers(int startPosition, int maxResult) throws Exception {
		return (List<User>) getObjects("FROM User", startPosition, maxResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListUsers(String param, int startPosition, int maxResult) throws Exception {
		if(param==null)
			param = "";
		return (List<User>) getObjects("FROM User u WHERE u.username like '%" + param + "%' OR " + "u.fullname like '%" + param + "%'" , startPosition, maxResult);
	}

	@Override
	public int getTotalUser() throws Exception {
		return (int) getTotalRecords("User");
	}

	@Override
	public User checkUserLogin(String username, String password) throws Exception {
		if (username == null || username.isEmpty()) {
			throw new Exception("Bạn chưa nhập username!");
		}
		if (password == null || password.isEmpty()) {
			throw new Exception("Bạn chưa nhập password!");
		}
		return (User) getObjectByUniqueField("User", "username", username);
	}

	

}
