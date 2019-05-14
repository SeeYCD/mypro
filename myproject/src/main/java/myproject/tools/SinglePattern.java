package myproject.tools;

import java.util.HashMap;
import java.util.Map;

import myproject.test.EnumDemo;

/**
 * 静态内部类实现单例、资源一次加载
 * @author user
 *
 */
public class SinglePattern {
	private static SinglePattern  singleP=null;
	private static Map<String, Object> useMap=null;
 	private  SinglePattern(){
		System.out.println("new oneTime SinglePattern!");
	}
 	private static class InnerClass{
 		private static SinglePattern singleInner=new SinglePattern();
 		private static Map<String, Object> innerMap=new HashMap<>();
 		static{
 			System.out.println("静态块加载！");
 			innerMap.put("test", "初始化OK");
 		}
 	}
 	public static SinglePattern getInstance(){
 		useMap=InnerClass.innerMap;
		singleP=InnerClass.singleInner;
		return singleP;
 	}
 	public static Map<String, Object> getInstanceMap(){
 		useMap=InnerClass.innerMap;
 		return useMap;
 	}
 	public static void main(String[] args) {
 		EnumDemo.ORACLE.getDesc();
 		SinglePattern.getInstance();
 		SinglePattern.getInstanceMap();
	}
}
