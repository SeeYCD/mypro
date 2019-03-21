package myproject.test.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

@CustomOne(name="/a")
public class UserCustomOne {
	@CustomOne(name="/b",value=1)
	public void doSomeThingOne(String str) {
		System.out.println("str++++"+str);
	}
	@RequestMapping(value="/c")
	public void doSomeThingTwo(String str) {
		System.out.println("str++++"+str);
	}

}
