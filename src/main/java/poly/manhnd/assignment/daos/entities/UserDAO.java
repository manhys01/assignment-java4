package poly.manhnd.assignment.daos.entities;

import java.util.List;

import poly.manhnd.assignment.entities.User;

public interface UserDAO {
	
	public boolean createUser(User u) throws Exception;

	public boolean updateUser(User u) throws Exception;

	public boolean deleteUser(User u) throws Exception;

	public List<User> getAllUsers() throws Exception;
	
	public List<User> getAllUsers(String param) throws Exception;

	public User getUser(int id) throws Exception;

	public List<User> getListUsers(int startPosition, int maxResult) throws Exception;
	
	public List<User> getListUsers(String param, int startPosition, int maxResult) throws Exception;

	public int getTotalUser() throws Exception;
	
	public User checkUserLogin(String username, String password) throws Exception;
}
