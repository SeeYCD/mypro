package myproject.frame.dao;

import java.util.List;

import myproject.frame.entity.User;

public interface DaoA {
	public List<String> testa1();
	public User login(User user);
	public User findUser(User user);
	public List<String> findUserRole(User user);
	public List<User> findAllUser();

}
