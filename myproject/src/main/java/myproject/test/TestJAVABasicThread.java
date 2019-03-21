package myproject.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础测试thread
 * @author user
 */
public class TestJAVABasicThread {
	private static Object lock = new Object();
	private static AtomicInteger at = new AtomicInteger(1);
	static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		/**
		 * 俩个线程交替输出0-100 如 偶：0 奇：1 偶：2
		 */
//		Thread t1 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				synchronized (lock) {
//					while (count<=100) {
//						System.out.println("偶:" + count++);
// 						lock.notifyAll();
//						try {
//							lock.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				synchronized (lock) {
//					while (count<=100) {
//						System.out.println("奇:" + count++);
// 						lock.notifyAll();
//						try {
//							lock.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		});
//		t1.start();
//		Thread.sleep(100);
//		t2.start();
		/**
		 * 三个线程交替输出123123123
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (lock) {
						if (at.intValue() == 1) {
							System.out.println("1:1");
							at.incrementAndGet();
							lock.notifyAll();
						}
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				while (true) {
					synchronized (lock) {
						if (at.intValue() == 2) {
							System.out.println("2:2");
							at.incrementAndGet();
							lock.notifyAll();
						}
					}
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			public void run() {

				while (true) {
					synchronized (lock) {
						if (at.intValue() == 3) {
							System.out.println("3:3");
							at.getAndSet(1);
							lock.notifyAll();
						}
					}
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}
}
