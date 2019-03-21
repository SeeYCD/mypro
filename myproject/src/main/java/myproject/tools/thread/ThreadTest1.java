package myproject.tools.thread;

/**
 * demo 守护线程
 * @author crh 2019年2月15日
 *
 */
public class ThreadTest1 {
	public static void main(String[] args) {
		Thread t1=new Thread(new Thread5());
		Thread t2=new Thread(new Thread5());
		Thread t3=new Thread(){
 			@Override
			public void run() {
 				System.out.println("匿名内部类创建thread");
 			}
		};
//		new Thread5(){
//			public void run(){
//				
//			}
//		};
		t1.setDaemon(true); //守护线程
		t2.setDaemon(true); //守护线程
		t1.start();		
		t2.start();
		t3.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" is over");
 	}
}
class Thread5 implements Runnable{
 	@Override
	public void run() {
 		while(true){
 			System.out.println(Thread.currentThread().getName()+"正在执行");
 		}
	}
	
}