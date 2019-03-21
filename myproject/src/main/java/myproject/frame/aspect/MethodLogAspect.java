package myproject.frame.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)//多个aop时，order小则最先执行，最后结束
public class MethodLogAspect {
	
	private Logger log=LoggerFactory.getLogger(MethodLogAspect.class);
 	/**
	 * 切入点
	 */

	@Pointcut(value = "execution(public * myproject..*Service*.*(..))")
	public void logonAspect(){
		log.info("执行aop异常记录！");
  	}
	/**
	 * 切入点执行异常
	 * @param jp
	 * @param e
	 */
	@AfterThrowing(value="logonAspect()",throwing="e")
	public void logOn(JoinPoint jp, Exception e){
		String methodName=jp.getSignature().getName();
		log.error("【AOP日志:方法"+methodName+"】运行出现异常:"+e.getMessage());
 	}
	@Before(value="logonAspect()")
	public void beforLog(JoinPoint jp){
		String methodName=jp.getSignature().getName();
		Object[] arg=jp.getArgs();
 		log.info("【2methodAOP日志befor:方法"+methodName+"】开始运行,参数是:"+arg);
//   		printMethod(jp.getThis());
 		printField(jp.getThis());
 		jp.getTarget();
 		log.info(jp.getThis().getClass().toString());
   	}
	@After(value="logonAspect()")
	public void afterLog(JoinPoint jp){
		String methodName=jp.getSignature().getName();
  		log.info("【2methodAop日志after:方法"+methodName+"】运行结束");
  	}
	//打印方法
	public void printMethod(Object t){
		for(Method m:t.getClass().getMethods()){
			System.out.println(t.getClass()+":"+m.getName());
		}
	}
	//打印属性
	public void printField(Object t){
		for(Field m:t.getClass().getFields()){
			System.out.println(t.getClass()+":"+m.getName()+":"+m.toString());
		}
	}
}
