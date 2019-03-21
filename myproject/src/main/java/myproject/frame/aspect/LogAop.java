package myproject.frame.aspect;

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
@Order(1)
public class LogAop {
	private Logger log=LoggerFactory.getLogger(MethodLogAspect.class);
 	/**
	 * 切入点
	 */

	@Pointcut(value = "execution(public * myproject..*Service*.*(..))")
	public void logonAspect(){
		log.info("LogAop异常记录！");
  	}
	/**
	 * 切入点执行异常
	 * @param jp
	 * @param e
	 */
	@AfterThrowing(value="logonAspect()",throwing="e")
	public void logOn(JoinPoint jp, Exception e){
		String methodName=jp.getSignature().getName();
		log.error("【LogAop日志:方法"+methodName+"】运行出现异常:"+e.getMessage());
 	}
	@Before(value="logonAspect()")
	public void beforLog(JoinPoint jp){
		String methodName=jp.getSignature().getName();
		Object[] arg=jp.getArgs();
 		log.info("【1LogAop日志Before:方法"+methodName+"】开始运行,参数是:"+arg);
 		log.info(jp.getThis().getClass().toString());
  	}
	@After(value="logonAspect()")
	public void afterLog(JoinPoint jp){
		String methodName=jp.getSignature().getName();
  		log.info("【1LogAop日志After:方法"+methodName+"】运行结束");
  	}
}
