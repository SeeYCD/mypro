package myproject.designpatterns.Adapter;
/**
 * 对象的适配器模式
 * @author user
 * 对象的适配器模式:当希望将一个对象转换成满足另一个新接口的对象时，
 * 可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 */
public class ObjectAdapter implements Targetable2{
	public Source source;

	public void method1() {
		source.method1();
		
	}

	@Override
	public void method2() {
		
	}
	

}
