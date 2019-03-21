package myproject.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.junit.Test;

 

 
public class TestConnection {
	/**
	 * 每次重新加载编译后的class，达到热加载效果
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		 try {
			 System.out.println("jiaz");
			Class.forName("oracle.jdbc.OracleDriver");
			 Connection connect = null;
	         connect = DriverManager.getConnection
	        		 ("jdbc:oracle:thin:@localhost:1521:orcl", "dqcheck", "dqcheck");
	         PreparedStatement preState = 
	        		 connect.prepareStatement("select  * from dual ");
	         System.out.println("开始执行");
	          preState.executeQuery();
		         System.out.println("执行OK");

		} catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
		}
		
		}

	}

	 