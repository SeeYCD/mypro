package maintest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class MainT {
//	@Test
	public void test1(){
			//不会加载类
			Class c1=ClassLoader1.class;
			//加载类
			System.out.println(ClassLoader1.num);
			try {
				//加载类
				c1=Class.forName("maintest.ClassLoader1");
			} catch (ClassNotFoundException e) {
	 			e.printStackTrace();
			}
		//先加载父类在加载当前类，在初始化父类后初始化当前类
		new ClassLoader1();
		}
//	@Test
	public void test2(){
		ClassLoader2 c=new ClassLoader1();
		c.ClassLoader2M1();
		if(c instanceof ClassLoader3){
			System.out.println("c--c3 is ok!");
		}else{
			System.out.println("c--c3 is not ok!");
 		}
	}
	//获取annotation
//	@Test
	public void test3(){
		Class c3=ClassLoader3.class;
 		Field[] fs=c3.getDeclaredFields();
		for(Field f:fs){
			String name=f.getName();
			String type=f.getType().toString();
			System.out.println("name-"+name);
			System.out.println("type-"+type);
			if("num".equals(name)){
				try {
					System.out.println("zhi-"+f.getInt(c3));
				} catch (IllegalArgumentException e) {
 					e.printStackTrace();
				} catch (IllegalAccessException e) {
 					e.printStackTrace();
				}
			}
		}
 	}
	//反射调用方法
//	@Test
	public void test4(){
		Class c=ClassLoader3.class;
		Class[] params={String.class,Integer.class};
		try {
			Method m=c.getMethod("classLoader3M1", params);
			Object[] obj={"ok",0};
			try {
				Object o=m.invoke(new ClassLoader3(),obj);
				System.out.println(o.toString());
			} catch (IllegalAccessException e) {
 				e.printStackTrace();
			} catch (IllegalArgumentException e) {
 				e.printStackTrace();
			} catch (InvocationTargetException e) {
 				e.printStackTrace();
			}
			
		} catch (NoSuchMethodException e) {
 			e.printStackTrace();
		} catch (SecurityException e) {
 			e.printStackTrace();
		}
	}
	//get set 方法
//	@Test
	public void test5(){
		ClassLoader3 c3=new ClassLoader3("jack",20);
		Class c=c3.getClass();
		Field[] fs=c.getDeclaredFields();
		for(Field f:fs){
			PropertyDescriptor pd = null;
			try {
				pd = new PropertyDescriptor(f.getName(), c);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			Method m=pd.getReadMethod();
			try {
				System.out.println(m.invoke(c3));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	//new constructor
	@Test 
	public void test6(){
		Class c3=ClassLoader3.class;
		Constructor[] cs=c3.getConstructors();
		for(Constructor c:cs){
			Class[] cc=c.getParameterTypes();
			System.out.println(cc.length);
			ClassLoader3 cl3=null;
			try {
				if(cc.length>0){
					cl3=(ClassLoader3) c.newInstance("jone",10);
					System.out.println(cl3.getAge());
 				}else{
 					cl3=(ClassLoader3) c.newInstance(null);
 				}
			} catch (InstantiationException e) {
 				e.printStackTrace();
			} catch (IllegalAccessException e) {
 				e.printStackTrace();
			} catch (IllegalArgumentException e) {
 				e.printStackTrace();
			} catch (InvocationTargetException e) {
 				e.printStackTrace();
			}
		}
				
	}
 }
