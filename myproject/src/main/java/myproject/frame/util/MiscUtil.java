package myproject.frame.util;

import org.apache.commons.lang.CharUtils;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * MiscUtil.java
 * 杂项工具类
 *
 * Modified By:	
 * Modified Date:	
 */
public class MiscUtil {
	
	public static final int DEFAULT_REMARK_LENGTH = 100;
	
	/** 返回一个UTF-8字符串的定长截断（3字节转2字节）
	 * @param str
	 * @param length
	 * @return
	 */
	public static String returnStringByLength(String str, int length) {
		int index = 0;
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<str.length();i++) {
			if (CharUtils.isAscii(str.charAt(i))) {
				index = index + 1;
			} else {
				index = index + 2;
			}
			if (index<=length) {
				sb.append(str.charAt(i));
			} else {
				break;
			}
		}
		return sb.toString();
	}
	/** 检查一个str是否在list<String>中出现
	 * @param str
	 * @param list
	 * @return
	 */
	public static boolean matchListValue(String str,List<String> list) {
		if (isNullOrEmpty(list)) {
			return false;
		}
		for (int i=0;i<list.size();i++) {
			if (str.equals(list.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/** 检查一个对象是否为空
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;
		
		if (obj instanceof String)
			return (obj.toString().trim()).equals("");

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			boolean empty = true;
			for (int i = 0; i < object.length; i++)
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			return empty;
		}
		return false;
	}
	
	/** 将一个对象转换成BigDecimal，如果为空则为0
	 * @param obj
	 * @return
	 */
	public static BigDecimal object2BigDecimal(Object obj) {
		if (isNullOrEmpty(obj)) {
			return new BigDecimal(0);
		}
		if (obj instanceof BigDecimal) {
			return (BigDecimal)obj;
		}else {
			return new BigDecimal(String.valueOf(obj));
		}
	}
	
	/** 将对象转换为Integer，如果为空则为0
	 * @param obj
	 * @return
	 */
	public static Integer object2Integer(Object obj) {
		if (isNullOrEmpty(obj)) 
			return Integer.valueOf(0);
		
		if (obj instanceof Integer) {
			return (Integer)obj;
		}else if (obj instanceof BigDecimal) {
			return ((BigDecimal)obj).intValue();
		}else {
			try {
				return Integer.parseInt(String.valueOf(obj));
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		}
	}
	
	/** 将对象转换成Long，如果为空则为0
	 * @param obj
	 * @return
	 */
	public static Long object2Long(Object obj) {
		if (isNullOrEmpty(obj)) 
			return Long.valueOf(0);
		
		if (obj instanceof Long) {
			return (Long)obj;
		}else if (obj instanceof BigDecimal) {
			return ((BigDecimal)obj).longValue();
		}else {
			return Long.parseLong(String.valueOf(obj));
		}
	}
	
	/** 将对象转换成Float，如果为空则为0
	 * @param obj
	 * @return
	 */
	public static Float object2Float(Object obj) {
		if (isNullOrEmpty(obj)) 
			return new Float(0);
		
		if (obj instanceof Float) {
			return (Float)obj;
		}else if (obj instanceof BigDecimal) {
			return ((BigDecimal)obj).floatValue();
		}else {
			return Float.parseFloat(String.valueOf(obj));
		}
	}
	
	/** 将整数格式化成指定长度的二进制字符串
	 * @param value
	 * @param len
	 * @return
	 */
	public static String formatInt(int value, int len) {
		StringBuffer sb = new StringBuffer(Integer.toBinaryString(value));
		int count = len-sb.length();
		for (int i=0;i<count;i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}
	
	/** 将long数字转换成指定长度的16进制字符串
	 * @param value
	 * @param len
	 * @return
	 */
	public static String formatLong(long value, int len) {
		StringBuffer sb = new StringBuffer(Long.toHexString(value));
		int count = len-sb.length();
		for (int i=0;i<count;i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}
	
	/** 将16进制字符串转换成byte数组
	 * @param value
	 * @return
	 */
	public static byte[] splitColorString(String value) {
		byte[] bytes = new byte[3];
		bytes[0] = Byte.parseByte(value.substring(0, 2));
		bytes[1] = Byte.parseByte(value.substring(2, 4));
		bytes[2] = Byte.parseByte(value.substring(4, 6));
		return bytes;
	}
    
	  /** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	  public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
}
