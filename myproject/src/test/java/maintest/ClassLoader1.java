package maintest;

public class ClassLoader1 extends ClassLoader2{
	static int num=10;
	static{
		System.out.println("static---ClassLoader1");
	}
	public ClassLoader1(){
		System.out.println("ClassLoader1()");
	}
	public void ClassLoader1M1(){
		System.out.println("ClassLoader1()");
	}
	
}
