package myproject.frame.web.shiro;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import myproject.frame.entity.User;
import myproject.frame.service.LoginService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限验证
 * @author user
 *
 */
public class UserRealm extends AuthorizingRealm{
	private static Logger logger=LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
	private LoginService loginService;
	
	public String getName(){
		return "userRealm";
 	}
	@Override
	/**
	 * 
	 * 登录校验、身份校验 shiro只是执行者，具体的执行内容可以自己定义
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String name=(String) token.getPrincipal();
		String passWord=new String((char[])token.getCredentials());
		User user=new User();
		User user2=null;
		user.setUserName(name);
		user.setPassWord(passWord);
  		user2=loginService.findUser(user);
		if(user2==null){
			throw new UnknownAccountException();
		}
		user2=loginService.login(user);
		if(user2==null){
			throw new IncorrectCredentialsException();
		}
		//如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(user, passWord,
				ByteSource.Util.bytes(name.getBytes()), getName());
	}
	
	/**
	 * 权限校验和资源访问的校验  和shiro.xml配置的内容进行比较；将登录者的相关信息，添加后，shiro自己进行校验
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user=(User) principals.getPrimaryPrincipal();
		int id=user.getId();
		List<String> listPermiss=new ArrayList<String>();
		listPermiss.add("/home/**");
		listPermiss.add("/home/task");
		listPermiss.add("/html/**");
 		
		Set<String> roleNames = new HashSet<String>();
		roleNames.addAll(loginService.findUserRole(user));
         
 		logger.info("测试授权！===》["+id+"]");
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(new HashSet<>(listPermiss));
		info.setRoles(roleNames);
		return info;
	}

	 @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        Cache c = getAuthenticationCache();
        logger.info("清除【认证】缓存之前");
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        super.clearCachedAuthenticationInfo(principals);
        logger.info("调用父类清除【认证】缓存之后");
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }

        // 添加下面的代码清空【认证】的缓存
        User user = (User) principals.getPrimaryPrincipal();
        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUserName(), getName());
        super.clearCachedAuthenticationInfo(spc);
        logger.info("添加了代码清除【认证】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【认证】缓存的大小:" + c.keys().size());
        if (cacheSize == 0) {
            logger.info("说明【认证】缓存被清空了。");
        }
    }

	    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info("清除【授权】缓存之前");
        Cache c = getAuthorizationCache();
        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        super.clearCachedAuthorizationInfo(principals);
        logger.info("清除【授权】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【授权】缓存的大小:" + cacheSize);

        for (Object o : c.keys()) {
            logger.info(o + " , " + c.get(o));
        }
        if (cacheSize == 0) {
            logger.info("说明【授权】缓存被清空了。");
        }

    }
}
