package myproject.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE}) //作用域，方法、（Class, interface）
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface CustomOne {
	String name();
	int value() default 0;
}
