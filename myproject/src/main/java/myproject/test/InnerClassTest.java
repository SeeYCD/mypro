package myproject.test;

import myproject.test.InnerClassTest.InnerClass;

public class InnerClassTest {
	private static  String n="0";
	private static  String key2="key2";
	private static  String key="key";

	public static void main(String[] args) {
		InnerClassTest ics=new InnerClassTest();
		InnerClass<Object, Object> in= ics.new InnerClass<>();
		StaticInnerClass<String, String> sc = 
				new StaticInnerClass<String, String>("1","a");
		System.out.println(sc.key);
	}
	public void main2() {
		InnerClassTest ics=new InnerClassTest();
 		StaticInnerClass<String, String> sic = 
				new StaticInnerClass<String, String>("1","a");
		System.out.println(sic.key);
 		InnerClass<String, String> is = 
				new InnerClass<String, String>("1","a");
		System.out.println(is.key);
		System.out.println(n);
 

	}
	//静态内部类
	static class StaticInnerClass<K, V> {
 		public static String sss;

 		private V value;
		private final K key;

		StaticInnerClass(K keys, V values) {
			this.key = keys;
			this.value = values;
			System.out.println(key);
			System.out.println(key2);
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}
	}
	//内部类
	class InnerClass<K, V> {
		private V value;
		private  K key;
		InnerClass() {
			System.out.println("innercalss-无参");
 		}
		InnerClass(K keys, V values) {
			this.key = keys;
			this.value = values;
			System.out.println(n);
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}
	}
}
class Demo extends InnerClass<Object, Object>{
 	Demo(InnerClassTest innerClassTest, Object keys, Object values) {
		innerClassTest.super(keys, values);
 	}
 	Demo(InnerClassTest innerClassTest) {
 		innerClassTest.super();
 	}
 	public static void main(String[] args) {
		InnerClassTest ics=new InnerClassTest();
 		Demo d=new Demo(ics);
 		System.out.println(InnerClassTest.StaticInnerClass.sss);
 		InnerClassTest.StaticInnerClass<String, String> sc = 
				new InnerClassTest.StaticInnerClass<String, String>("1","a");
 		
		InnerClassTest.InnerClass<String, String> is = 
				ics.new InnerClass<String, String>("1","a");
		
  	}
}

/**
 * 测试有关A继承了B后，A对于B的内部类权限；
 * 结论：testIneerPermission(),观察此方法可以看到，A是可以和B一样使用内部(前提权限没有限制的情况下);
 * @author crh 2019年3月18日
 */
class OutClass1 extends InnerClassTest{
	public void test22 (){
		main2();
 	}
}
class OutClass2{
	private OutClass1 out1;
	public OutClass2 (){
		out1=new OutClass1();
	}
 	public void testIneerPermission(){
 		out1.new InnerClass<Object, Object>();
 		OutClass1.StaticInnerClass<String, String> ss
 			=new InnerClassTest.StaticInnerClass<>("", "");
 	} 
}