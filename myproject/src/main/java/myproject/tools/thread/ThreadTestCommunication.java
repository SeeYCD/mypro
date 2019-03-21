package myproject.tools.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程间通信
 * 
 * @author Chen
 *
 */

public class ThreadTestCommunication {

	public static void main(String[] args) {
		CountDownLatch cd = new CountDownLatch(3);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + ":is ok!");
				cd.countDown();
			}
		});
		t1.setName("t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + ":is ok!");
				cd.countDown();
			}
		});
		t2.setName("t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + ":is ok!");
				cd.countDown();
			}
		});
		t3.setName("t3");
		t1.start();
		t2.start();
		t3.start();
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ":is runnable!");
 				try {
					cd.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ":is ok!");
			}
		});
		t4.setName("t4");
		t4.start();
 	}
}
