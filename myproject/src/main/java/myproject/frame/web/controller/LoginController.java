package myproject.frame.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myproject.frame.entity.Constants;
import myproject.frame.entity.Response;
import myproject.frame.entity.User;
import myproject.frame.service.LoginService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope(value="prototype")
@RequestMapping(value="/")
public class LoginController {
	private static Logger log=LoggerFactory.getLogger(LoginController.class);

 	@Autowired
	private LoginService loginService;
//	@RequestMapping(value="/**/*",method=RequestMethod.GET)
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Response login(User use,HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		String userName=use.getUserName();
		String passWord=use.getPassWord();  
		log.debug(userName+":正在登录....");
 		UsernamePasswordToken token=new UsernamePasswordToken(userName, passWord);
		Subject subject=SecurityUtils.getSubject();
		  try {
	            subject.login(token);   
	        } catch (UnknownAccountException | IncorrectCredentialsException uae) {
	            // 捕获未知用户名异常
	        	return new Response("用户或密码错误!", "error",null);
	        } catch (ExcessiveAttemptsException eae) {
	            // 捕获错误登录过多的异常
	        	return new Response("错误登录次数过多!", "error",null);
	        }
	        User user = loginService.findUser(use);
	        subject.getSession().setAttribute("user", user);
	        req.getSession().setAttribute("user", user);
	       //如果存在登录前访问的页面 登录后，访问那个页面   shiro支持，没有登录前的页面保存
    	   SavedRequest sr=WebUtils.getSavedRequest(req); 
    	   if(sr!=null){
    		 String url=sr.getRequestUrl();
//	    		 res.sendRedirect(url);
    		 user.setUrl(url);
    		 log.debug("登录前访问url:"+url);
    	    }
 	   return new Response("登录成功!", "suc",user);
 	}
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	@ResponseBody
	public Response logout(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException, IOException{
		String ss="";
		Subject subject=SecurityUtils.getSubject();
		if(log.isTraceEnabled()){
			User user=(User) subject.getPrincipal();
			log.debug("登出："+user.getUserName()+ss);
		}
		subject.logout();
//		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
 		return new Response("成功","suc",null);
	}
	
	//在正真登录之前判断是否
    @RequestMapping(value = "/unauthorizedUrl", method = RequestMethod.GET)
    public String unauthorizedUrl(HttpServletRequest request, HttpServletResponse response) {
       /* if (request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))) {
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.print("unauthorizedUrl");//session失效
                out.flush();
            } catch (IOException e) {
                log.error("获取writer异常", e);  
            }
            return null;
        } else {
            return "unauthorizedUrl";
        }*/
    	return "unauthorizedUrl";  
    }
    
  //没有登录的跳转地址
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void unLogin(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String url = WebUtils.getSavedRequest(request).getRequestUrl();
    	log.info("登录前的url====》"+url);
    	request.getRequestDispatcher("/html/login.html").forward(request, response);
//    	response.sendRedirect(request.getContextPath()+"/html/login.html");
    }
  //获取登录信息
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserInfo(HttpServletRequest request, HttpServletResponse response) 
    		 {
    	User loginUser=new User();
    	Response res=new Response("ok!", Constants.ERROR, loginUser);
    	if(SecurityUtils.getSubject()!=null){
    	       loginUser=(User) SecurityUtils.getSubject().getPrincipal();
    	       res.setStatu(Constants.SUCCESS);
    	       res.setUser(loginUser);
     	}
//    	List<User> list=loginService.findAllUser();
 	   return res;
     }
}
