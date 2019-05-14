package myproject.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日志测试
 * @author user
 *
 */
public class CopyOfTestClass {
private static Logger log=LoggerFactory.getLogger(CopyOfTestClass.class);
	
//	public  static void main(String[] test1){
// 		log.info("infot");
//		log.trace("tracet");
//		log.debug("debugt");
//		log.error("errort");
//		log.warn("warnt");
//		LoggerContext lc=(LoggerContext) LoggerFactory.getILoggerFactory();
//		StatusPrinter.print(lc);
//	}
	public void test2(){
 		log.info("infot");
		log.trace("tracet");
		log.debug("debugt");
		log.error("errort");
		log.warn("warnt");
	}
}
