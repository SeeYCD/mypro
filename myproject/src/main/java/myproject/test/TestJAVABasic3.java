package myproject.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 基础测试,泛型
 * 
 * @author user
 *
 */
public class TestJAVABasic3 {
	public TestJAVABasic3() {
		System.out.println("TestJAVABasic2 constructor");
	}

	public static void main(String[] args) {
//		TestFanXing1<String> tfx = new TestFanXing1<String>("fxtest");
//		System.out.println(tfx.GetFX());
		
		TestFanXing2 tfx2=new TestFanXing2();
		List list1=new ArrayList();
		list1.add("1A");
		list1.add(new Father("jack"));
		
		tfx2.getList1(list1);
		tfx2.getList2(list1);
		List list2=new ArrayList();
		list2.add(new Father("jack"));
		list2.add(new Son("jack-son"));
		tfx2.getList4(list2);
		ArrayList<?> a=new ArrayList<>();
		
 	}
}

class TestFanXing1<T> {
	private T t;

	public TestFanXing1() {
	}

	public TestFanXing1(T tt) {
		this.t = tt;
	}

	public T GetFX() {
		return t;
	}
}

class TestFanXing2 extends TestFanXing1<String> {
	public void getList1(List<?> list) {// 不确定参数的类型
		for (Object o : list) {
			System.out.println("getList1:"+o.toString());
		}
	}
	public  <T> void getList2(List<T> list) {// 确定参数的类型是T一类,可以使用T类型
		for (T t : list) {
			System.out.println("getList2:"+t.toString());
		}
	}
	public void getList3(List<? extends Father> list) {// 确定参数的类型是Father及其子类
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("getList3:"+it.next());
		}
	}
	public void getList4(List<? super Father> list) {// 确定参数的类型是Father及其父类
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("getList4:"+it.next());
		}
	}
	
}

class TestFanXing3 <T extends TestFanXing2> {

}

class Father {
	private String name;
	public Father(){
 	}
	public Father(String n){
		this.name=n;
	}
	@Override
	public String toString() {
		return "Father [name=" + name + "]";
	}
 }

class Son extends Father{
 	private String name;
	public Son(String n){
		this.name=n;
	}
	@Override
	public String toString() {
		return "Son [name=" + name + "]";
	}
}