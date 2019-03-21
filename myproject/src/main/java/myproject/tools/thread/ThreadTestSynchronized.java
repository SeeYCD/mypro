package myproject.tools.thread;

/**
 * demo 守护线程
 * 
 * @author crh 2019年2月15日
 *
 */
public class ThreadTestSynchronized {
	private String s1 = new String("sdwd");
	private String s2 = new String("sdwd");

	public static void main(String[] args) {
		final ThreadTestSynchronized tt = new ThreadTestSynchronized();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				mehtod1();
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				mehtod2(2);
			}
		};
		Thread t3 = new Thread() {
			@Override
			public void run() {
				tt.mehtod3();
			}
		};
		Thread t4 = new Thread() {
			@Override
			public void run() {
				tt.mehtod2(4);
			}
		};
		Thread t5 = new Thread() {
			@Override
			public void run() {
				tt.mehtod4();
			}
		};
		Thread t6 = new Thread() {
			@Override
			public void run() {
				tt.mehtod5();
			}
		};
		/*
		 * t1和t2线程调用的都是类对象的同步方法，互斥， t3调用的是实例对象，和t1、t2互不影响,
		 * t4通过实例调用的类对象方法，和t2同时执行时，还是会互斥
		 * t5、t6，调用的锁定不同属性的同步方法，不互斥
 		 */
		// t1.start();
//		t2.start();
		// t3.start();
//		 t4.start();
		 t5.start();
		 t6.start();


	}

	// 通过内置锁，锁定了类对象，所以类对象的方法调用会互斥
	public synchronized static void mehtod1() {
		for (int i = 0; i < 100; i++) {
			System.out.println("mehtod1");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 通过内置锁，锁定了类对象，所以类对象的方法调用会互斥
	public synchronized static void mehtod2(int m) {
		for (int i = 0; i < 100; i++) {
			System.out.println("mehtod2" + m);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 通过内置锁，锁定了实例对象，所以实力你对象的同步方法调用会互斥
	public synchronized void mehtod3() {
		for (int i = 0; i < 100; i++) {
			System.out.println("mehtod3");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 通过内置锁，锁定了实例对象的属性
	public void mehtod4() {
		synchronized (s1) {
			for (int i = 0; i < 100; i++) {
				System.out.println("mehtod4");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 通过内置锁，锁定了实例对象的属性
	public void mehtod5() {
		synchronized (s2) {
			for (int i = 0; i < 100; i++) {
				System.out.println("mehtod5");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
