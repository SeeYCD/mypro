package myproject.servlet;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
//@WebServlet("/*")
public class HelloWordServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		System.out.println("/HelloWordServlet");
//		try {
//			res.getWriter().write("this is hello test!");
//
//			} catch (IOException e) {
// 			e.printStackTrace();
//		}
	}
	
	@Test
	public void t1(){
		SecureRandom sr=new SecureRandom();
		System.out.println(sr.nextDouble());
		System.out.println(Math.random());
		System.out.println(sr.nextInt(9999));
	}
//	@Test
	public void t2(){
		double i=Math.random();
		System.out.println(i);
	}
}
