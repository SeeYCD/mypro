package myproject.designpatterns.proxy;

public class DemoTest {
	public static void main(String[] args) {
		Sourceable source = new Proxy();
		source.method01();
	}
}
