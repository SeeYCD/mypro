package myproject.frame.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
/**
 * 用户访问页面、方法权限判断
 * @author user
 *
 */
public class PermissionInterceptor implements HandlerInterceptor{
	private Logger log=LoggerFactory.getLogger(PermissionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    log.info("====================【PermissionInterceptor日志】++++++++++++++++++++===");
 		String host=request.getHeader("REFERER");
		String ip = null;
		request.getRemoteAddr();
		//X-Forwarded-For：Squid 服务代理
	    String ipAddresses = request.getHeader("X-Forwarded-For");
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
		        //Proxy-Client-IP：apache 服务代理
		        ipAddresses = request.getHeader("Proxy-Client-IP");
		    }
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
		        //WL-Proxy-Client-IP：weblogic 服务代理
		        ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		    }
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
		        //HTTP_CLIENT_IP：有些代理服务器
		        ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		    }
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
		        //X-Real-IP：nginx服务代理
		        ipAddresses = request.getHeader("X-Real-IP");
		    }

	    //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
	    if (ipAddresses != null && ipAddresses.length() != 0) {
	        ip = ipAddresses.split(",")[0];
	    }

	    //还是不能获取到，最后再通过request.getRemoteAddr();获取
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        ip = request.getRemoteAddr();
	    }
		
	    log.info("====================【PermissionInterceptor日志】访问者IP:"+ip);
		if(!StringUtils.isEmpty(host)){
			log.info("【PermissionInterceptor日志】访问页面:"+host.toString());
 		}
 		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
 	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
