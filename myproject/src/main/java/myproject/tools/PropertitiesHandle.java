package myproject.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class PropertitiesHandle {
	/*
	 * 读取class下的配置文件  通过class路径   读取字符流
	 */
	public static void readPropertyChar(String classPath,String key) throws IOException{
		InputStreamReader inStream=
				new InputStreamReader(PropertitiesHandle.class.getClassLoader().getResourceAsStream(classPath), "UTF-8");
 		Properties p=new Properties();
		p.load(inStream);
		System.out.println(p.get(key));
	}
	/*
	 * 读取class下的配置文件  通过class路径   读取字节流
	 */
	public static void readPropertyByte(String classPath,String key) throws IOException{
		InputStream inStream=PropertitiesHandle.class.getClassLoader().getResourceAsStream(classPath);
 		Properties p=new Properties();
		p.load(inStream);
		System.out.println(p.get(key));
	}
	/*
	 * 使用绝对路径 ，获取配置文件
	 */
	public static void readFile(String fileName,String key) throws IOException{
		InputStream ins=new FileInputStream(new File(fileName));
		Properties p1=new Properties();
		p1.load(ins);
		System.out.println("p1->"+p1.get(key));
		
		InputStreamReader insr=new InputStreamReader(new FileInputStream(new File(fileName)),"UTF-8");
		Properties p=new Properties();
		p.load(insr);
		System.out.println("p->"+p.get(key));

	}
	/*
	 * 设置properties的值
	 */
	public static void setProperties(String pathname,String key, String value) throws IOException{
		InputStream ins=new FileInputStream(new File(pathname));
		Properties p=new Properties();
		p.load(ins);
		
		OutputStream ou=new FileOutputStream(new File(pathname));
		OutputStreamWriter otw=new OutputStreamWriter(ou, "UTF-8");
		p.setProperty(key, value);
		p.store(otw, "更新-->"+key);
		
 	}
	/*
	 * 写classpath下文件
	 */
	public static void writeFile(String filePath,String key,String value) throws IOException, FileNotFoundException{
		InputStream ins=PropertitiesHandle.class.getClassLoader().getResourceAsStream(filePath);
		Properties p=new Properties();
		p.load(ins);
		//取全路径
		String classPath=PropertitiesHandle.class.getClassLoader().getResource(filePath).getPath();
		OutputStreamWriter otw=
				new OutputStreamWriter(new FileOutputStream(new File(classPath)), "UTF-8");
		p.setProperty(key, value);
		p.store(otw, "update or add -->"+key);
	}
}
