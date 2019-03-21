package myproject.test.annotation;

import java.lang.reflect.Method;

/**
 * 存储class和方法名
 * @author user
 *
 * @param <T>
 */
public class CustomParseEntity<T> {
	private Class<T> classF;
	private Method method;
	 
	public Class<T> getClassF() {
		return classF;
	}
	public void setClassF(Class<T> classF) {
		this.classF = classF;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}

}
