package myproject.designpatterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 代理模式： 1.静态代理，要求目标对象和代理对象要实现相同接口； 基础测试：动态代理，方法只有通过代理类调用才能走代理方法
 * 2.JDK动态代理，目标对象必须实现接口,这个是JDK动态代理的硬性要求，也是不灵活之处；
 * 3.cglib代理，也叫子类代理，通过创建目标对象的子类当做代理类，对目标对象是否实现接口没有硬性要求；
 * 
 * @author crh 2019年2月26日
 *
 */
public class TestProxy {
//	@Test//JDK动态代理
	public void test() {
 		LogProxy lp = new LogProxy();
		// 生成了代理对象
		UserManager proxy1 = (UserManager) lp.instanceProxy(new UserManagerImpl());
		// 代理对象调用方法
		proxy1.insertUser("jack");
		printMethod(proxy1);
		// myproject.designpatterns.proxy.dynamic.$Proxy0 代理类的class
		System.out.println(proxy1.getClass());
		// 生成了代理对象
		UserManager2 proxy2 = (UserManager2) lp.instanceProxy(new UserManagerImpl());
		// 代理对象调用方法 ,内部在调用方法，不走代理
		proxy2.insertUser2("jack2");
	}

	@Test//CGlib动态代理：
	public void test2() {
		UserManagerImpl2 target = (UserManagerImpl2) new TransactionProxy(
				new UserManagerImpl2()).createInstanceProxy();
		target.insertUser("shiwu");
		System.out.println(target.getClass());
		//class myproject.designpatterns.proxy.dynamic.UserManagerImpl2$$EnhancerByCGLIB$$3e00aed4
 	}
	
	@Test
	public void test3(){
		//工厂
		LogProxy lp1 = new LogProxy();
		LogProxy lp2 = new LogProxy();
 		// 生成了代理对象
		UserManager proxy1 = 
				(UserManager) lp1.instanceProxy(new UserManagerImpl());
		System.out.println(proxy1.getClass());
 		// 生成了代理对象
		UserManager proxy2 = 
				(UserManager) lp2.instanceProxy(proxy1);
		proxy2.delUser("1111");
	}
	public void printMethod(Object t){
		for(Method m:t.getClass().getMethods()){
			System.out.println(t.getClass()+":"+m.getName());
		}
	}
}

/**
 * JDK动态代理类
 * 
 * @author crh 2019年2月26日
 *
 */
class LogProxy implements InvocationHandler {
	// 目标对象
	private Object targetObject;

	// 获取代理对象，传递目标对象实例参数
	public Object instanceProxy(Object realObject) {
		this.targetObject = realObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(), this);
	}

	@Override
	// 通过代理对象调用方法，将执行invoke方法
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object returns;
		System.out.println("log-jdk-s:" + method.getName());
		returns = method.invoke(targetObject, args);
		System.out.println("log-jdk-e:" + method.getName());
		return returns;
	}
}

/**
 * CGlib动态代理类
 * 
 * @author crh 2019年2月26日
 *
 */
class TransactionProxy implements MethodInterceptor {
	private Object target;

	public TransactionProxy(Object target) {
		this.target = target;
	}

	public Object createInstanceProxy() {
		Enhancer eh = new Enhancer();
		eh.setSuperclass(target.getClass());
		eh.setCallback(this);
		return eh.create();
	}

	@Override
	public Object intercept(Object arg0, Method method, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		System.out.println("cglib-方法名：" + method.getName());
		Object ret = method.invoke(target, arg2);
		System.out.println("cglib-方法名：" + method.getName());
		return ret;
	}

}

interface UserManager {
	public void insertUser(String name);

	public void delUser(String name);

}

interface UserManager2 {
	public void insertUser2(String name);

	public void delUser2(String name);
}

class UserManagerImpl implements UserManager, UserManager2 {

	@Override
	public void insertUser(String name) {
		System.out.println("insert:" + name);
		delUser(name); // 在方法内部调用其他的实例方法，不会经过代理
	}

	@Override
	public void delUser(String name) {
		System.out.println("del:" + name);
	}

	@Override
	public void insertUser2(String name) {
		System.out.println("insert2:" + name);
		delUser2(name);
	}

	@Override
	public void delUser2(String name) {
		System.out.println("del2:" + name);

	}
}

class UserManagerImpl2 {

	public void insertUser(String name) {
		System.out.println("insert:" + name);
		delUser(name); // 在方法内部调用其他的实例方法，不会经过代理
	}

	public void delUser(String name) {
		System.out.println("del:" + name);
	}

}
