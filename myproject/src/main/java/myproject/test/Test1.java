package myproject.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

class InitClass2 {
	public static String f0 = "22";
	public static String f1 = "1";
	public static String f2;
	public String f3 = "3";
	static {
		System.out.println("运行父类静态代码");
	}

	public InitClass2() {
		System.out.println("InitClass2实例==f3-" + f3);

	}
}

class SubInitClass2 extends InitClass2 {
	public static String f5 = "4";
	static {

		System.out.println("运行子类静态代码" + f5);
	}

	public SubInitClass2() {
		System.out.println("SubInitClass2实例==");
	}
}

public class Test1 {
	/**
	 * 每次重新加载编译后的class，达到热加载效果
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		URL url = null;
		boolean b = true;
		while (b) {
			try {
				url = new URL("file:D:\\CRH\\classloader\\");
				URLClassLoader ul = new URLClassLoader(new URL[] { url });
				Class<?> testc = ul.loadClass("TestClass1");
				Object obj = testc.newInstance();
				testc.getMethod("test1").invoke(obj);
				Thread.sleep(2000);
			} catch (IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException
					| ClassNotFoundException | MalformedURLException e) {
				b = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void test2() {
		// try {
		// FileInputStream fi=new FileInputStream(new File(""));
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// }
		short dso = 15;
		byte b = 2, e = 3;// ------1
		long f = 111111;// ------2
		System.out.println(f);
		String s = "";
		s.length();
		String[] ss = new String[10];
		System.out.println(ss[9]);
		System.out.println(ss.length);
		String str = "abcdefg";
		System.out.println(str.substring(2, 5).indexOf('d'));
		System.out.println(5.00 - 4.10);
		InitClass2 p = new InitClass2();
		SubInitClass3 t = new SubInitClass3();
		SubInitClass2 t2 = new SubInitClass2();
		char c1 = '\"';
		char c3 = 'X';
		// char c4='';
		char c5 = 65;
		Boolean b11 = new Boolean("abcd");
		int x = (int) (1.23);
		float f1 = 123;
		byte b1 = 127;
		String s1=new String("1112");
		s1=s1.intern();
		String s2="1112";
		System.out.println(s1==s2);
	}

}

class SubInitClass3 extends InitClass2 {
	public static String f5 = "4";

}
abstract class SubInitClass4{
	public static String f5 = "4";
	public abstract   void t();

}
abstract class SubInitClass5 extends SubInitClass4{
 	public synchronized   void t(String n){
		
	};
 }