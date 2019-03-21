package myproject.tools.thread;

/**
 * demo 线程状态 // 调用其他线程的join的方法为什么会导致当前线程进入阻塞
 * 线程执行完run方法后会调用线程的notifyAll()方法
 * @author crh 2019年2月15日
 *
 */
public class ThreadTestState {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						sleep(500);
					} catch (InterruptedException e) {
 						e.printStackTrace();
					}
					System.out.println(i);
				}
 			};
		};
		t.start();
		synchronized (t) {
			try {
				t.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 30; i++) {
			System.out.println("主线程第" + i + "次执行！");
			if (i > 10) {
//				try {
//					t.join();// t线程合并到主线程中，主线程停止执行过程，转而执行t线程，直到t执行完毕后继续。
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}
}