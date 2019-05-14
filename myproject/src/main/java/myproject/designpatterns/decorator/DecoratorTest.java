package myproject.designpatterns.decorator;
/**
 * 装饰器模式
 * @author user
 *
 */
public class DecoratorTest {
	public static void main(String[] args) {
		Sourceable source = new Source();
		Sourceable obj = new Decorator(source);
		obj.method01();
	}
}
