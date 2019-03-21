package myproject.frame.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





















import myproject.frame.entity.Response;
import myproject.frame.entity.User;
import myproject.frame.service.LoginService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录
 * @author user
 *
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	private static Logger log=LoggerFactory.getLogger(HomeController.class);
 	@Autowired
	private LoginService loginService;
	@RequestMapping(value="/task",method=RequestMethod.GET)
	@ResponseBody
	public Response login(User use,HttpServletRequest req,HttpServletResponse res){
		log.info("/task:"+use.getId());
 	   return new Response("获取成功!", "suc",null);
 	}
	@RequestMapping(value="/unAuthorization",method=RequestMethod.GET)
	@ResponseBody
	public String testUnAuthorization(User use,HttpServletRequest req,HttpServletResponse res){
		log.info("/unAuthorization:"+use.getId());
 	   return "unAuthorization";
 	}
	
	@RequestMapping(value="/justdo",method=RequestMethod.GET)
 	public String justdo(User use,HttpServletRequest req,HttpServletResponse res){
		log.info("/justdo:"+use.getId());
 	   return "justdo";
 	}
}
