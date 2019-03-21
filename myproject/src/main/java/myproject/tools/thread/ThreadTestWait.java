package myproject.tools.thread;

/**
 * 启动两个线程, 一个（t1）输出 1,3,5,7…99, 另一个（t2）输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
 * 思路：先启动t2,并且锁定某对象n2，在启动t1,t1执行一次后，解锁n2,锁定n1(等待t2执行后，解锁n1);
 * @author crh 2019年2月14日
 *
 */

public class ThreadTestWait {
	public static ThreadTestWait n1 = new ThreadTestWait();
	public static ThreadTestWait n2 = new ThreadTestWait();
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
 		t2.start();
		t1.start();
	}
}

class Thread1 extends Thread {
	// 输出1,3,5.....
	public void run() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 != 0) {
				System.out.println(i);
				try {
					synchronized (ThreadTestWait.n2) {//先获取对象锁
						ThreadTestWait.n2.notifyAll();
					}
					synchronized (ThreadTestWait.n1) {//先获取对象锁
						ThreadTestWait.n1.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Thread2 extends Thread {
	// 输出2,4,6.....
	public void run() {
		try {
			synchronized (ThreadTestWait.n2) {//先获取对象锁
				ThreadTestWait.n2.wait();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
				synchronized (ThreadTestWait.n1) {
					ThreadTestWait.n1.notifyAll();
				}
				try {
					if(i!=100){
						synchronized (ThreadTestWait.n2) {
							ThreadTestWait.n2.wait();
						}
					}
 				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
