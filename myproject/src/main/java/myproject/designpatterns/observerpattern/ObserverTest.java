package myproject.designpatterns.observerpattern;
/**
 * 观察者模式的demo
 * @author crh 2019年3月20日
 *
 */
public class ObserverTest {
	//适合一对多的消息推送
	public static void main(String[] args) {
		ConcreteSubject cs = new ConcreteSubject();
		ObserverUser o1 = new ObserverUser("A");
		ObserverUser o2 = new ObserverUser("B");
		ObserverUser o3 = new ObserverUser("C");
		ObserverUser o4 = new ObserverUser("D");
		cs.registObserver(o1);
		cs.registObserver(o2);
		cs.registObserver(o3);
		cs.registObserver(o4);
 		cs.pushNews("今日头条001");
 		
 		//println
// 		A收到消息：今日头条001
// 		B收到消息：今日头条001
// 		C收到消息：今日头条001
// 		D收到消息：今日头条001
	}
}
