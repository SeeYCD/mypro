package myproject.frame.service;

import java.util.List;

import myproject.frame.entity.Response;
import myproject.frame.entity.User;

public interface LoginService {
	static int i=0;
	public User login(User user);
 	public User findUser(User user);
	public List<String> findUserRole(User user);
	public List<User> findAllUser();
	default  List<User> findAllUser(int ig){
		System.out.println("default findalluser");
		return null;
 	};


}
