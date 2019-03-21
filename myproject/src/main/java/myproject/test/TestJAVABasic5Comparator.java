package myproject.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 基础测试,俩种比较器的测试
 * 
 * @author user
 *
 */
public class TestJAVABasic5Comparator{

	public static void main(String[] args) {
		//通过comparable比较
		List<Person1> list1=new ArrayList<Person1>();
		Person1 p11=new Person1(1);
		Person1 p12=new Person1(2);
		Person1 p13=new Person1(1);
		list1.add(p11);list1.add(p12);list1.add(p13);
		Collections.sort(list1);
		for(Person1 p:list1){
			System.out.println("cable:"+p.getAge());
		}
		
		
		//通过comparator比较
		List<Person2> list2=new ArrayList<Person2>();
		Person2 p21=new Person2(4);
		Person2 p22=new Person2(2);
		Person2 p23=new Person2(3);
		list2.add(p21);list2.add(p22);list2.add(p23);
//		Collections.sort(list2, new PersonComparator());
		Collections.sort(list2, new Comparator<Person2>(){
			@Override
			public int compare(Person2 o1, Person2 o2) {
				if(o1.getAge()>o2.getAge())
					return 1;
				if(o1.getAge()<o2.getAge())
					return -1;
				return 0;
			}
			
		});

		for(Person2 p:list2){
			System.out.println("cator:"+p.getAge());
		}
//		cable:1
//		cable:1
//		cable:2
//		cator:2
//		cator:3
//		cator:4

	}
}
//要想使用comparable，必须实现其接口
class Person1 implements Comparable<Person1>{
	private int age;
	public Person1(int age){
		this.age=age;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Person1 o) {
		if(this.age>o.getAge())
			return 1;
		if(this.age<o.getAge())
			return -1;
		return 0;
	}
	
}
class Person2{
	private int age;
	public Person2(int age){
		this.age=age;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
//使用comparator进行对象比较，无需侵入对象
class PersonComparator implements Comparator<Person2>{
	@Override
	public int compare(Person2 o1, Person2 o2) {
		if(o1.getAge()>o2.getAge())
			return 1;
		if(o1.getAge()<o2.getAge())
			return -1;
		return 0;
	}
 	
}
