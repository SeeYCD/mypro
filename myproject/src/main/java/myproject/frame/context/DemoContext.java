package myproject.frame.context;

import java.util.Scanner;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 测试ApplicationContextAware接口功能
 * @author  2019年1月18日
 *
 */
public class DemoContext implements ApplicationContextAware{
	private  static ApplicationContext context=null ;
	private  static Logger logger=LoggerFactory.getLogger(DemoContext.class);
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		logger.info("lalalalalalalallalla---DemoContext.context--init");
		context=applicationContext;
		
	}
	
 	@SuppressWarnings("unchecked")
	public static <T>T getBean(String beanName){
 		return (T) (context.getBean(beanName)==null?null:context.getBean(beanName));
	}
 	@Test
	public void prints() {
    	System.out.println("请输入最大值：");
    	Scanner scanner = new Scanner(System.in); 
    	int count =transform(scanner);
   		boolean b=false;
 		b=count%2==0;
 		int level = 0;
 		level = count/2+1;
 		System.out.println("层数："+level);
 		inversion(level,count);
 		triangle(level,count);
	}
 	public String appendString(int num,String value){
		StringBuilder sb = new StringBuilder();
  		for (int k = 0; k < num; k++) {
			sb.append(value);
		}
		return sb.toString();
 		
 	}
 	//转换数字
 	public int transform(Scanner scanner){
 		String input=scanner.nextLine();
 		int num=0;
 		try{
 			num=Integer.parseInt(input);
 			return num;
  		}catch(NumberFormatException n){
 			System.out.println("请输入数字：");
 			return transform(scanner);
 		}
 		finally{
 			
 			if(scanner!=null){
 	 			scanner.close();
  			}
 		}
    }

 	//倒置三角triangle
 	public void inversion(int level,int count ){
 		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			int temp = count - 2 * i;
 			sb.append(appendString(i," "));
 			sb.append(appendString(temp,"*"));
  			sb.append(appendString(i," "));
 			sb.append("\n");

		}
		System.out.println(sb);
 	}
 	//三角形
 	 	public void triangle(int level,int count ){
 	 		StringBuilder sb = new StringBuilder();
 			for (int i = 0; i < level; i++) {
 				int temp = count - 2 * (level-i);
 	 			sb.append(appendString(level-i," "));
 	 			sb.append(appendString(temp,"*"));
 	  			sb.append(appendString(level-i," "));
 	 			sb.append("\n");

 			}
 			System.out.println(sb);
 	 	}
 	
}
