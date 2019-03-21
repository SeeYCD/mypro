package myproject.frame.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * ConfigFileCreator.java
 * 读取Properties配置文件
 * 
 * @version 1.0
 * @author wangtz
 * Written Date: 2017-08-03
 * 
 * Modified By:	
 * Modified Date:	
 */
public class ConfigFileReader {

	public static Properties reader(String path) {
		InputStream in = ConfigFileReader.class.getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();   
		try {    
		   p.load(in);    
		} catch (IOException e1) {    
		   e1.printStackTrace();    
		}    
		return p;
	}
	
	
	public static String getVarByKey(String key){
		Properties p = reader("datasourcecfg/dscfgpath.properties");
		String var = p.getProperty(key);
		
		return var;
	}
	
	public static String getPropertiesByKey(String key){
		Properties p = reader("jdbc.properties");
		String var = p.getProperty(key);
		return var;
	}
	
	public static String getPro4MailByKey(String key){
		Properties p = reader("javamail.properties");
		String var = p.getProperty(key);
		return var;
	}
}
