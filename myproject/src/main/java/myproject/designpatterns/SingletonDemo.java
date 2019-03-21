package myproject.designpatterns;

public class SingletonDemo {
    private static String testStatic = "11111";  
 	public static void main(String[] args) {
 		System.out.println(SingletonDemo.testStatic);
  		SingletonFactory sss=new SingletonDemo().new SingletonFactory();
 		getInstance();
	}
	/* 私有构造方法，防止被实例化 */  
    private SingletonDemo() {  
    }  
    /*4. 此处使用一个静态内部类来维护单例 ,线程安全的原因是JVM保证一个类的加载是线程安全的*/  
    public static class StaticSingletonFactory {  
    	static{
    		System.out.println("SingletonFactory-static");
    	}
        private static SingletonDemo instance = new SingletonDemo();  
    }  
    private  class SingletonFactory {  
    	 public SingletonFactory(){
    		 System.out.println("SingletonFactory");
    	 }
     } 
    /* 获取实例 */  
    private static SingletonDemo getInstance() {  
        return StaticSingletonFactory.instance;  
    }  
  
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return getInstance();  
    }  
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
