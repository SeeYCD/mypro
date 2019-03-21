package myproject.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * 基础测试jdk8接口与抽象类的区别 1.静态内部类的初始化，不会导致外部类的初始化 2.
 * 
 * @author user
 */
public class TestJAVABasicInterface implements DemoInterface {
	public static void main(String[] args) {
		DemoInterface di1 = new TestJAVABasicInterface();
		di1.findAllUser("di");
		System.out.println(DemoStaticClass.IsDemoStaticInnerClass.isi);
		List<String> l = new ArrayList<>();
		l.add("1");
		l.add("3");
		l.add("2");
		l.forEach(System.out::println);
		Class dc = DemoConstructor.class;
		try {
			dc.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(DemoStaticFinalClass.IDD + "---"
				+ DemoStaticFinalClass.SDD + "===" + DemoStaticFinalClass.SDDL);
		
 	}
	
	class DemoChm{
		private String key;
		private int value;
		public DemoChm(String key,int value){
			this.key=key;
			this.value=value;
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return (int) (value * (Math.random()*10000));
		}
		@Override
		public boolean equals(Object obj) {
			DemoChm dc=(DemoChm) obj;
 			return key.equals(dc.getKey());
		}
		public String getKey(){
			return key;
		}
		public int getValue(){
			return value;
		}
	}
	@Test
	public void testC(){
		final Map<DemoChm, String> cmap=new ConcurrentHashMap<DemoChm, String>();
 		for(int i=0;i<10;i++){
 			final int n=i;
 			Thread t1=new Thread(new Runnable() {
 	 			@Override
 				public void run() {
 					 for(int i=(100000*(n*0)+1);i<100000*(n*0+1);i++){
 						 cmap.put(new DemoChm(String.valueOf(i), 100), String.valueOf(i));
 					 }
 				}
 			}) ;
 			t1.start();
 		}
  		
		Thread t3=new Thread(new Runnable() {
 			@Override
			public void run() {
 				try {
					Thread.sleep(90);
				} catch (InterruptedException e) {
 					e.printStackTrace();
				}
 				DemoChm dc=new DemoChm(String.valueOf(5), 100);
				 for(int i=1;i<2000000;i++){
					 if(cmap.get(dc)==null){
  						 System.out.println("t3:"+cmap.get("1"));
 					 }
 				 }
			}
		}) ;
		Thread t4=new Thread(new Runnable() {
 			@Override
			public void run() {
 				try {
					Thread.sleep(90);
				} catch (InterruptedException e) {
 					e.printStackTrace();
				}
 				DemoChm dc=new DemoChm(String.valueOf(1), 100);
 				 for(int i=0;i<2000000;i++){
					 if(cmap.get(dc)==null){
  						 System.out.println("t4:"+cmap.get("1"));
 					 }
 				 }
			}
		}) ;
		Thread t5=new Thread(new Runnable() {
 			@Override
			public void run() {
 				try {
					Thread.sleep(90);
				} catch (InterruptedException e) {
 					e.printStackTrace();
				}
 				DemoChm dc=new DemoChm(String.valueOf(111), 100);
 				 for(int i=0;i<2000000;i++){
					 if(cmap.get(dc)==null){
  						 System.out.println("t5:"+cmap.get("1"));
 					 }
 				 }
			}
		}) ;
 		t3.start();t4.start();t5.start();
	}

	@Override
	public void login(String name) {
		System.out.println("this:" + name);
	}

	@Override
	public String findAllUser(String name) {
		System.out.println("TestJAVABasicInterface findalluser" + name);
		return name;
	}

	@Override
	public void login(int name) {

	}
}

// 测试接口
/*
 * 1.如果一个接口是继承另外一个接口的，这两个接口中都有同样的默认方法，那么选择父接口中的方法，也就是只保留父接口中的默认方法。
 * 2.当一个类实现两个接口的时候，其中一个接口实现了默认方法，但是另一个不管是默认方法还是抽象方法，编译器报接口冲突，让我们自己解决。
 * 3.接口中的默认方法和继承的父类方法冲突了，那么这个时候会选择父类中的方法，而不是接口中的默认方法。这个也叫做类优先原则，
 * 它可以保证与Java7的兼容性。也就是说，在接口中实现的一个默认方法，它不会对Java8之前写的代码产生影响。
 * 所以，我们也不能在接口中定义toString()和equals()这样的接口，因为根据类优先的原则，Object中的这些方法会保留。
 */
interface DemoInterface {
	static int i = 0;

	public void login(String name);

	public void login(int name);

	// 可以直接供子类使用，无需再重写此方法
	default String findAllUser(String name) {
		System.out.println("DemoInterface findalluser" + name);
		return null;
	};
}

// 测试抽象类
abstract class DemoAbstract {
	// 抽象方法不 private default
	protected abstract void testPrivateM();

	protected void testM1() {

	};
}

// 测试静态内部的调用是否触发外部类的初始化
class DemoStaticClass {
	static {
		System.out.println("加载DemoStaticClass");
	}
	static int i = 0;

	static class IsDemoStaticInnerClass {
		static {
			System.out.println("加载IsDemoStaticInnerClass");
		}
		static int isi = 0;
	}
}

// 测试构造函数链
class DemoConstructor extends DemoConstructorFather {
	public DemoConstructor() {
		System.out.println("DemoConstructor:");
	}

	public DemoConstructor(String name) {
		super(name);
		System.out.println("DemoConstructor:" + name);
	}
}

class DemoConstructorFather {
	public DemoConstructorFather() {
		System.out.println("DemoConstructorFather");
	}

	public DemoConstructorFather(String name) {
		System.out.println("DemoConstructorFather:" + name);
	}
}

// 测试编译期常量
class DemoStaticFinalClass {
	// 编译期常量
	public static final int IDD = 100;
	public static final String SDD = "100sss";
	// 运行期常量
	public static final int SDDL = "100sss".length();
	static {
		System.out.println("加载DemoStaticFinalClass");
	}
}