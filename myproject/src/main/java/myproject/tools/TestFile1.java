package myproject.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;

import org.junit.Test;

public class TestFile1 {
	private static DataSource jndiDataSource = null;
	private static String DBPoolName = "";

	/*
	 * 测试获取配置文件内容
	 */
	// @Test
	public void test1() {
		System.out.println("测试读取文件-----配置文件");
		try {
			Properties pro = new Properties();
			InputStream in1 = TestFile1.class
					.getResourceAsStream("/config/config.properties");
			pro.load(in1);
			in1.close();
			Object o = pro.getProperty("name");
			System.out.println("00000------" + o);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/*
	 * 测试获取配置文件内容，部署在weblogic服务器，打成war包后，配置文件需要放在web-inf下 <init-param>
	 * <param-name>configFile</param-name>
	 * <param-value>/WEB-INF/config/poolconfig.properties</param-value>
	 * </init-param>
	 */
	// @Test
	public void test2(ServletConfig config) {
		Connection conn = null;
		try {
			String file = config.getInitParameter("configFile");
			String path = config.getServletContext().getRealPath(file);
			InputStream path2 = config.getServletContext().getResourceAsStream(
					file);
			Properties properties = new Properties();
			properties.load(new FileInputStream(path));
			DBPoolName = properties.getProperty("DBPoolName");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		try {
			Context ctx = (Context) new InitialContext();
			// 生产使用
			// jndiDataSource = (DataSource) ctx.lookup(DBPoolName);
			// 本地启用
			jndiDataSource = (DataSource) ctx.lookup("java:comp/env/"
					+ DBPoolName);
			conn = jndiDataSource.getConnection();
			conn.setAutoCommit(false);
			conn = jndiDataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
		} catch (NamingException e) {
		}
	}

	@Test
	public void testIO() {
		// read file
		BufferedReader br = null;
		BufferedWriter bw = null;
		String filePath = "C:/Users/user/Desktop/test.txt";
		String newFilePath = "C:/Users/user/Desktop/testNew.txt";

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(filePath)), "UTF-8"), 1024 * 1024);
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(newFilePath), "UTF-8"));
			String text = null;
			StringBuilder temp = null;
 			while ((text = br.readLine()) != null) {
 				temp=new StringBuilder();
				text=text.replaceAll("aaa", "");
				temp.append(text).append(System.lineSeparator());
				bw.write(temp.toString());
 			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeRead(br);
			closeWriter(bw);
		}
	}

	public void closeRead(Reader read) {
		if (read != null) {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeWriter(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
