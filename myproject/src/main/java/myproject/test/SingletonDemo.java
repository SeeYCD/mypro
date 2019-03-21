package myproject.test;

public class SingletonDemo {

}

//1.饿汉模式 初始化创建，空间换时间 ，不存在安全问题，
class Singleton1{
	private static Singleton1 singleton=new Singleton1();
	public static Singleton1 getInstance(){
		return singleton;
	}
}
//2.懒汉模式  在被调用时创建对象，时间换空间 ，线程不安全
class Singleton2{
	private static Singleton2 sing=null;
	public static Singleton2 getInstance(){
		return sing==null?new Singleton2():sing;
	}
}
//3.双重安全锁模式
class SingleTon3 {
	private static volatile SingleTon3 INSTANCE = null;
	public static SingleTon3 getInstance() {
		if (INSTANCE == null) {
			synchronized (SingleTon3.class) {
				if (INSTANCE == null) {
					INSTANCE = new SingleTon3();
				}
			}
		}
		return INSTANCE;
 	}
}
