package myproject.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;

public class RegexDemo {
	public static void main(String[] args) {
		String value = "";
		String demo = "";

		String regex = "";

		String demo1 = "012=34s";
		value = StringUtils.replaceAll(demo1, "^\\d*[=]", "---");
		System.out.println("demo1-:" + value);

		String demo2 = "who is";
		value = StringUtils.replaceAll(demo2, "\bis\b", "---");
		System.out.println("demo2-:" + value);
		// 加入特定限制条件「[]」
		String demo3 = "w1sS";
		value = StringUtils.replaceAll(demo3, "[w[0-9]]+", "---");
		System.out.println("demo3-:" + value);

		String demo4 = "ws1sS";
		value = StringUtils.replaceAll(demo4, "[0-9[A-Z]]+", "---");
		System.out.println("demo4-:" + value);
		// []中加入^后加再次限制条件「[^]」 非
		String demo5 = "ws1sS";
		value = StringUtils.replaceAll(demo5, "[^0-9[A-Z]]+", "---");
		System.out.println("demo5-:" + value);
		String demo6 = "ws1s2S";
		value = StringUtils.replaceAll(demo6, "[^0-9]+", "---");
		System.out.println("demo6-:" + value);
		// 在限制条件为特定字符出现0次以上时，可以使用「*」
		String demo7 = "ws1s2S";
		value = StringUtils.replaceAll(demo7, "[0-9]*", "---");
		System.out.println("demo7-:" + value);
		String demo8 = "ws1s2S";
		value = StringUtils.replaceAll(demo8, "w[0-9]*s", "---");
		System.out.println("demo8-:" + value);
		// 在限制条件为特定字符出现1次以上时，可以使用「+」
		String demo9 = "w01s2S";
		value = StringUtils.replaceAll(demo9, "w[0-9]+s", "---");
		System.out.println("demo9-:" + value);
		// 在限制条件为特定字符出现有0或1次时，可以使用「?」
		String demo10 = "--w333w3e01s2S";
		value = StringUtils.replaceAll(demo10, "^-?", "---");// 匹配w+一个3或者0个3，即是：w、w3者这俩种
		System.out.println("demo10-:" + value);
		// 匹配大小写字母和数字下划线
		String demo11 = "__w333w3e01s2S";
		regex = "[0-9a-zA-Z_]+";
		System.out.println("demo11-:" + demo11.matches(regex));
		// 校验身份证
		String demo12 = "12345678909867840";
		regex = "^\\d{15}|\\d{17}(\\d|X|x$)";
		System.out.println("demo12-:" + demo12.matches(regex));
		//
		String demo13 = "ppp<\"hh\">";
		regex = "<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>";
		System.out.println("demo13-:" + demo13.matches(regex));

		String demo14 = "ppp<\"hh\"\'hh\'888888>";
		value = StringUtils.replaceAll(demo14,
				"<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>", "0");//
		System.out.println("demo14-:" + value);

		String demo15 = "ppp< kkk gg gg onclick=>";
		value = StringUtils.replaceAll(demo15, "<+.*(onload|onclick)+\\s*=",
				"0");//
		System.out.println("demo15-:" + value);
		// 提取整数
		String demo16 = "ppp<3 kkk5 3gg3 gg*%^& o3ncl3i3c3k3.0144ds=>";
		value = StringUtils.replaceAll(demo16, "[^\\d]+", "-");//
		System.out.println("demo16-:" + value);
		// 匹配正整数
		Pattern pattern = null;
		Matcher matcher = null;
		demo = "0";
		regex = "^0|([1-9][0-9]*$)";
 		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(demo);
		System.out.println("demo17-:" + matcher.matches());
		//匹配数字
		demo = "01";
		regex = "^\\d+$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(demo);
		System.out.println("demo18-:" + matcher.matches());
		//长度为8-10的用户密码（以字母开头,字母、数字、下划线）
		demo = "_wwww_01t";
		regex = "^[a-zA-Z]\\w{7,10}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(demo);
		System.out.println("demo19-:" + matcher.matches());
		//匹配中文 [\u4e00-\u9fa5]
		demo = "喂喂喂1";
		regex = "^[\u4e00-\u9fa5]*$";
		regex = "^[\u4e00-\u9fa5]{0,}$";
 		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(demo);
		System.out.println("demo20-:" + matcher.matches());
		//简单的身份证号验证：
		demo = "23451234512345123x333";//234512345123451
 		regex = "^\\d{15}|\\d{17}[\\dXx]$";
 		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(demo);
		System.out.println("demo21-:" + matcher.find());
  		System.out.println("demo21-:" + matcher.groupCount());
  		System.out.println("demo21-:" + matcher.group());
 		System.out.println("demo21-:" + matcher.replaceAll("dog"));
  		System.out.println("demo21-:" + matcher.groupCount());
  		//
  		pattern=Pattern.compile("");
  		matcher=pattern.matcher("");
  		matcher.find();
 
	}

}
