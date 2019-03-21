package myproject.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础测试
 * 
 * @author user
 *
 */
public class TestJAVABasic {
	private String dd;

	// private static Logger log=LoggerFactory.getLogger(TestJAVABasic.class);
	// @Test
	public void test1() {
		System.out.println(1 >> 8);
		int i = 0;
		System.out.println(Character.digit("1".charAt(i++), 10));
		System.out.println("1".charAt(0) < '0');
		System.out.println(Integer.parseInt("1"));
		String s = "";
	}

	// @Test
	public void testInteger() {
		// integer
		int i = 1111;// 1
		Integer in = 1111;// 2
		System.out.println(i == in);// 3
		/*
		 * 反编译之后的字节码如下，可以得知， 第2行，发生自动装箱,调用了valueOf(int)；
		 * 3行在比较是发生了自动拆箱,intValue(); integer类型和int类型在交互时会发生拆装箱
		 */
		// int i = 1111;
		// Integer in = Integer.valueOf(1111);
		// System.out.println(i == in.intValue());
		long l = 1;
		Long ll = 1l;
		System.out.println(l == ll);
		// 反编译结果
		// long l = 1L;
		// Long ll = Long.valueOf(1L);
		// System.out.println(l == ll.longValue());
		char c = 'a';
		byte b = 12 + 'b';
		short s = 23 + 'a';
		// short s2 = 23 +c ;//编译报错
		int i2 = b + s + c;
		double d1 = 1 + i2;
		System.out.println(i2 == d1);
		System.out.println(i2);
		// 反编译结果
		// char c = 'a';
		// byte b = 110;
		// short s = 120;
		// int i2 = b + s + c;
		// System.out.println(i2);
		System.out.println(getS().dd);
		System.out.println(getS2());
	}

	public TestJAVABasic getS() {
		TestJAVABasic ss = new TestJAVABasic();
		try {
			return ss;
		} catch (Exception e) {

		} finally {
			ss.dd = "234";
		}
		return ss;
	}

	public String getS2() {
		String s1 = "11";
		try {
			return s1;
		} catch (Exception e) {

		} finally {
			s1 = "22";
		}
		return s1;
	}

	private String testPrivate() {
		return "11";
	}

	// @Test
	public void testprivate() {
		TestJAVABasic tj = new TestPrivate();
		System.out.println(tj.testPrivate());
		TestPrivate tj2 = new TestPrivate();
		System.out.println(tj2.testPrivate2());

	}

	@Test
	public void test4() {
		byte b = 1;
		byte[] bb = new byte[] { 11, 1, 1, 1 };
		byte[] bb2= new byte[2];
		byte[] bb3 ={ 96, 97, 97, 97 };
		byte[] srtbyte = {97,98,98};

 		String s1 = new String();
		System.out.println(s1.valueOf(b));
 		try {
			String	s2 = new String(bb3,"UTF-8");
			System.out.println(s2);
 			String res = new String(srtbyte,"UTF-8");
			System.out.println(res);
			byte b4=1;
			int i=b4;
			short s=b4;
			long l=b4;
			float f=b4+100000000000000000l;
			double d=b4;
 			System.out.println(i+s+l);
			System.out.println(f);
 			byte b5=(byte) -130;
			System.out.println(b5);
			long ds=1;
			byte b6=127;
			b6=(byte) (b6+1);
			b6+=ds;
 			System.out.println(b6);
 			System.out.println(0.3);

 			System.out.println(3*0.1 == 0.3);
  		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		}
	}
}

class TestPrivate extends TestJAVABasic {
	private String testPrivate() {
		return "22";
	}

	public String testPrivate2() {
		return testPrivate();
	}
}
