package myproject.test;

/**
 * 枚举测试
 * @author user
 *
 */
public enum EnumDemo {
	ORACLE("ORACLE","1"),MYSQL("MYSQL","2");
	
	private String desc;//描述
	private String value;//值
	static {
		System.out.println("chushih");
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private EnumDemo(String desc,String value){
		System.out.println(desc);
		this.desc=desc;
		this.value=value;
	}
	
	public static void main(String[] arg){
//  		String i=EnumDemo.MYSQL.getValue();
//  		System.out.println(i);
		System.out.println("11");
 	}
 }
