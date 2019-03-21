package myproject.frame.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import myproject.frame.dao.DaoA;
import myproject.frame.entity.User;
import myproject.frame.service.LoginService;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
//@Transactional(propagation=Propagation.REQUIRED)
public class LoginServiceImpl implements LoginService{
	private static Logger log=LoggerFactory.getLogger(LoginServiceImpl.class);
	 
	public LoginServiceImpl(){
		super();
		System.out.println("_________________logins初始化啦222------------------");
 	}
	@Autowired
	private DaoA daoa;
 	public User login(User user) {
  		return daoa.login(user);
 	}
	@Override
	public User findUser(User user) {
		return daoa.findUser(user);
	}
	@Override
	public List<String> findUserRole(User user) {
 		return daoa.findUserRole(user);
	}
	@Override
	public List<User> findAllUser() {
		PageHelper.offsetPage(0, 2);
		List<User> l=daoa.findAllUser();
//		log.debug("分割风==================风==================="+l.size());
		PageInfo<User> page=new PageInfo<>(l);
//		log.debug("分割风==================风==================page="+page.getTotal());
  		return page.getList();
	}
	
	@Test
	public void test2(){
		List<User> l=new Page<>();
 		System.out.println(l instanceof LinkedList);
 		System.out.println(l instanceof ArrayList);
  	}
}
