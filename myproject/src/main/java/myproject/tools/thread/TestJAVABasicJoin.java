package myproject.tools.thread;

/**
 * 基础测试threadlocal 测试join后，到底释放了谁的锁 我们一般synchronized
 * (o){。。。。。}的时候，在代码块里调用的是O.wait(), 所以才会释放有关O的同步锁，误让我们以为同步方法里调用wait方法就会释放锁，
 * 主要看锁定的对象和调用wait的是不是一个对象；而案例里面t1释放的是线程t的同步锁， 所以还是锁定着o对象，导致线程t2并没有执行；
 * 
 * @author user
 */
public class TestJAVABasicJoin {
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 测试join后，到底释放了谁的锁
		 */
		Object o = new Object();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + ":"
							+ i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o) {
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName()
								+ ":" + i);
						if (i == 5) {
							try {
								System.out.println(Thread.currentThread()
										.getName() + "开始join");
								t.join();
								System.out.println(Thread.currentThread()
										.getName() + "结束join");
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
		t.start();
		t1.start();
		t2.start();

	}

}
