package myproject.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日志测试
 * @author user
 *
 */
public class TestClass{
private static Logger log=LoggerFactory.getLogger(TestClass.class);
	
//	public  static void main(String[] test1){
// 		log.info("infot");
//		log.trace("tracet");
//		log.debug("debugt");
//		log.error("errort");
//		log.warn("warnt");
//		LoggerContext lc=(LoggerContext) LoggerFactory.getILoggerFactory();
//		StatusPrinter.print(lc);
//	}
//	@Test
	public void test1(){
		log.info("infot");
		log.trace("tracet");
		log.debug("debugt");
		log.error("errort");
		log.warn("warnt");
		CopyOfTestClass c=new CopyOfTestClass();
		c.test2();
	}
}
