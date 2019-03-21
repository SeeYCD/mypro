package myproject.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * 基础测试IO体系
 * 
 * @author user
 *
 */
public class TestJAVABasic7IO {
	public static void main(String[] args) {
		try {
			InputStream is1 =new FileInputStream(new File("C:/Users/user/Desktop/demo.txt"));
//			byte[] bb=new byte[1024*6];
//   			is1.read(bb);
//  			System.out.println(new String(bb));
 			System.out.println("=============");
 			//字节数组输出流
 			ByteArrayOutputStream bo=new ByteArrayOutputStream();
 			int x=0;
 			while((x=is1.read())!=-1){
 				bo.write(x);
 			}
 			System.out.println(bo);
 			System.out.println("=============");

 			//字节缓存输入流
			InputStream is = new BufferedInputStream(new FileInputStream(
					new File("C:/Users/user/Desktop/demo.txt")),
					5 * 1024 * 1024);
			//字符缓冲输入操作， FileReader继承了inputstreamreader，提供文件操作中，字节流转向字符流的功能
			BufferedReader reader = new BufferedReader(
					new FileReader("C:/Users/user/Desktop/demo.txt"),5 * 1024 * 1024);
			String br = null;
			while ((br = reader.readLine()) != null) {
				System.out.println(br);
			}
			
			//深度克隆，将对象输出到流中，也可以将对象输出到文件中；俩种选择
			ByteArrayOutputStream bot=new ByteArrayOutputStream();
 			ObjectOutputStream oos= new ObjectOutputStream(bot);
			oos.writeObject(new String("234"));
 			ObjectInputStream ois= new ObjectInputStream(new ByteArrayInputStream(bot.toByteArray()));
 			System.out.println(ois.readObject().toString());
  		}catch (ClassNotFoundException e) {
 			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("文件异常：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO异常：" + e.getMessage());
		}
	}
	
}
