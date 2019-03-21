package myproject.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础测试,测试类的初始化、实例化，做了哪些事情
 * 
 * @author user
 *
 */
public class TestJAVABasic2 {
    Person person = new Person("TestJAVABasic2");
    static{
//        System.out.println("TestJAVABasic2 static");
    }     
    public TestJAVABasic2() {
        System.out.println("TestJAVABasic2 constructor");
    }    
    public static void main(String[] args) {
        new MyClass();
    }
//    @Test
    public void test(){
    	List<String> list=new ArrayList<>();
    	List<String> list2=new LinkedList<>();
    	list2.add("1");
    	list2.add("a");
     	for(String s:list2){
//    		System.out.println(s);
    	}
    	Map< String, String> hash=new HashMap<>();
    	Map< String, String> chash=new ConcurrentHashMap<>();
    	Map< String, String> chasht=new Hashtable<>();
    	Set<HashDemo> hashset=new HashSet<>();
    	hashset.add(new HashDemo(1));
    	hashset.add(new HashDemo(2));
    	hashset.add(new HashDemo(3));
      	for(HashDemo s:hashset){
    		System.out.println("hashsetdemo:"+s.getI());
    	}
      	for(Map.Entry<String, String> entry:hash.entrySet()) {
      		entry.getKey();
    	}
 //    	chasht.put(null, "11");
//    	System.out.println(chasht.get(null));
    	System.out.println(8^(8>>>16));
    	Class c=String[].class;
    	System.out.println(1|2);
    	
    	StringBuffer sb=new StringBuffer();
    	StringBuilder sbu=new StringBuilder();

     	
      }
//    @Test
    public void test1(){
    	Set<String> set=new TreeSet<String>();
    	set.add("4");
    	set.add("2");
    	set.add("3");
     	set.add("2");
     	System.out.println("1".compareTo("3"));
      	for(String s:set){
//    		System.out.println(s);
    	}
      	List<String> list2=new LinkedList<>();
    	list2.add("1");
    	list2.add("2");
      	Iterator it=list2.iterator();
//    	while(it.hasNext()){
//    		System.out.println(it.next());
//    		it.remove();
//    		list2.add("3");
//    	}
    	Map< String, String> hash=new TreeMap<>();
     	hash.put("5", "1");
     	hash.put("1", "1");
    	hash.put("2", "2");
     	hash.put("3", "3");
     	hash.put("4", "4");
     	Iterator its =hash.entrySet().iterator();
//     	while(its.hasNext()){
//     		System.out.println(its.next());
//     	}
    	Set st=new HashSet<>();
    	st.add("1");
    	st.add("2");
    	st.add("3");
    	st.add("4");
    	System.out.println(st.getClass());
    	Object[] oo=st.toArray();
    	for(Object d:oo){
    		System.out.println(d);
    	}
      }
    @Test
    public void test3(){
    	HashDemo h1=new HashDemo(1);
    	HashDemo h2=new HashDemo(2);
    	HashDemo h3=new HashDemo(3);
    	HashDemo h4=new HashDemo(4);
    	HashDemo h5=new HashDemo(5);
    	HashDemo h6=new HashDemo(6);
    	HashDemo h7=new HashDemo(7);
    	HashDemo h8=new HashDemo(8);
    	HashDemo h9=new HashDemo(9);
    	HashDemo h10=new HashDemo(10);
    	HashDemo h11=new HashDemo(11);
    	HashDemo h12=new HashDemo(12);
    	HashDemo h13=new HashDemo(13);

      	Map< HashDemo, String> hash=new HashMap<>();
      	hash.put(h7, "1");
      	hash.put(h2, "1");
       	hash.put(h11, "1");
      	hash.put(h5, "1");
      	hash.put(h4, "1");
      	hash.put(h3, "1");
      	hash.put(h9, "1");
       	hash.put(h10, "1");
      	hash.put(h1, "1");
      	hash.put(h8, "1");
//      	hash.put(h7, "1");
//      	hash.put(h13, "1");

//      	hash.put(h6, "1");hash.put(h11, "1");hash.put(h12, "1");
      	Iterator<Entry<HashDemo, String>> its =hash.entrySet().iterator();
      	
     	while(its.hasNext()){
     		System.out.println(its.next().getKey());
     	}
    }
}
class Person{
    static{
//        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}
class MyClass extends TestJAVABasic2 {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }
    public MyClass() {
        System.out.println("myclass constructor");
    }
}
class HashDemo implements Comparable<HashDemo>{
	@Override
	public String toString() {
		return "HashDemo [i=" + i + "]";
	}
	private int i;
	public HashDemo(int nu) {
        this.i=nu;
    }
    @Override
    public int hashCode() {
     	return 1;
    }
    @Override
    public boolean equals(Object obj) {
    	
       	return this.i==((HashDemo)obj).i;
    }
	@Override
	public int compareTo(HashDemo o) {
  		return this.i-o.i;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
}