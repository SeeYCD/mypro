package maintest;

import org.springframework.stereotype.Service;

@Service
public class ClassLoader3 extends ClassLoader2{
//	static int num=10;
	static{
		System.out.println("static---ClassLoader3");
	}
	public String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int age;
	/**
	 * asasas
	 */
	public ClassLoader3(){
		System.out.println("ClassLoader3()");
	}
	public String classLoader3M1(String s,Integer i){
		System.out.println("method-classLoader3M1-s-"+s+"-i-"+i);
		return "result-classLoader3M1";
	}
	public ClassLoader3(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("ClassLoader3() --name--"+name+"--age--"+age);
	}
	
}
