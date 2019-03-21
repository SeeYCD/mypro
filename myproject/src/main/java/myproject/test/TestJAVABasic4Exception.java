package myproject.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 基础测试异常体系
 * 
 * @author user
 *
 */
public class TestJAVABasic4Exception extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
	 try {
		InputStream in =new FileInputStream(new File(""));
	} catch (FileNotFoundException e) {
 		e.printStackTrace();
	}
  	}
}
