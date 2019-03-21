package myproject.tools.thread;

/**
 * 死锁demo
 * 两个线程都需要对方释放相关对象锁才能执行下去，这样会导致死锁
 * @author crh 2019年2月14日
 *
 */

public class ThreadTestDealLock {
	public static ThreadTestDealLock n1 = new ThreadTestDealLock();
	public static ThreadTestDealLock n2 = new ThreadTestDealLock();

	public static void main(String[] args) {
		boolean b1 = true, b2 = false;
		Thread3 t1 = new Thread3(b1);
		Thread3 t2 = new Thread3(b2);
		new Thread(t1).start();
		new Thread(t2).start();
	}
}

class Thread3 implements Runnable {
	private boolean flag;

	public Thread3(boolean f) {
		this.flag = f;
	}

	public void run() {
		if (flag) {
			synchronized (ThreadTestDealLock.n1) {
				System.out.println("执行11，等待n2");
				synchronized (ThreadTestDealLock.n2) {
					System.out.println("执行12");
				}
			}
		} else {
			synchronized (ThreadTestDealLock.n2) {
				System.out.println("执行22，等待n1");
				synchronized (ThreadTestDealLock.n1) {
					System.out.println("执行21");
				}
			}
		}

	}

}