package myproject.frame.service.impl;

import myproject.frame.service.ServiceB;

import org.springframework.stereotype.Service;

@Service
public class ServiceBImpl implements ServiceB{
	public void testb1() {
  		System.out.println("testb1!" );
  		String s=null;
  		System.out.println(s.length());
	}
}
