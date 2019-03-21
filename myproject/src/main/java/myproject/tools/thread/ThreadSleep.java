package myproject.tools.thread;

/**
 * 在A线程中调用其他B线程的sleep(XX)方法，也会让当A线程睡眠；
 * 如果时xx时间未到，A执行完了，B线程也会唤醒；
 * 如果时xx时间到，A未执行完了，B线程也会唤醒；
 * @author user
 */
public class ThreadSleep {
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 测试join后，到底释放了谁的锁
		 */
		Object o = new Object();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName()
							+ ":" + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t1 = new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				synchronized (o) {
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName()
								+ ":" + i);
						if (i == 5) {
							try {
								System.out.println(Thread.currentThread()
										.getName() + "开始sleep");
								t.sleep(1000);
								System.out.println(Thread.currentThread()
										.getName() + "结束sleep");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);// 确保t1先执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o) {
					for (int i = 0; i < 5; i++) {
						System.out.println(Thread.currentThread().getName()
								+ ":" + i);
					}
				}
			}
		});
		t.setName("t");
 		t1.setName("t1");
		t2.setName("t2");
  		t.start();
		t1.start();
		t2.start();

	}

}
