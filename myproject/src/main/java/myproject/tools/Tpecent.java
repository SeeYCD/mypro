package myproject.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

import org.junit.Test;

public class Tpecent {
	/**
	 * 测试百分
	 */
//	@Test
	public void test1(){
		DecimalFormat df=new DecimalFormat("00.####%");
		double b1=0.34198297189;
		double b2=1;
		double b3=b1/b2;
		System.out.println(b3);
 		String pecent=df.format(b3);
		System.out.println(pecent);
	}
	/**
	 * 获取系统ip
	 */
	@Test
	public void test2(){
		 InetAddress in;
		try {
			in = InetAddress.getLocalHost();
			String ip= in.getHostAddress();
			 System.out.println(ip);
		} catch (UnknownHostException e) {
 			e.printStackTrace();
		}
		
}
}
