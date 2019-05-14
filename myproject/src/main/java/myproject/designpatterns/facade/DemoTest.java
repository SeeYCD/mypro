package myproject.designpatterns.facade;
/**
 * 外观模式
 * @author user
 *
 */
public class DemoTest {
	public static void main(String[] args) {
		Computer computer = new Computer();  
        computer.startup();  
        computer.shutdown();  
	}
}
