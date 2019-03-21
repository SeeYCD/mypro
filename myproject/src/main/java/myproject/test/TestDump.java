package myproject.test;

import java.util.ArrayList;
import java.util.List;

public class TestDump {
//	private byte [] b=new byte[2014*2014];
	private static int _1MB=1024*1024;
	int i;
	public void test1(){
		++i;
		test1();
	}
	public static void test2(){
		byte [] b1=new byte[2 * _1MB];
		byte [] b2=new byte[2 * _1MB];
		byte [] b3=new byte[2 * _1MB];
		byte [] b4=new byte[2 * _1MB];
		byte [] b5=new byte[1 * _1MB];
	}
	public static void main(String[] args) {
		/*
		 * 1.堆内存溢出
		 */
//		List<byte[]> list=new ArrayList<byte[]>();
//		int i=0;
//		while(true){
//			++i;
//			list.add(new TestDump().b);
//			System.out.println(i);
//		}
//		TestDump gg=null;
		/*
		 * 2.栈内存溢出
		 */
//		try{
//			gg=new TestDump();
//			gg.test1();//递归调用，创建了大量的栈帧，超出了虚拟机栈的深度
//		}catch(Throwable t){
//			System.out.println(gg.i);
//			t.printStackTrace();
//		}
		/*
		 * 3.gc的触发   
		 * -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails
		 * -Xloggc:gc.log  -XX:SurvivorRatio=8
		 */
		 test2();
	}
}
