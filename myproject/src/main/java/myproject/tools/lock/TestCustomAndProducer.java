package myproject.tools.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.用 wait-notify ;2.用 lock 写一段代码来解决生产者-消费者问题
 * 
 * @author crh 2019年3月18日
 *
 */
public class TestCustomAndProducer {
	public static void main(String[] args) {
		Storage st = new Storage();
		Thread custom1 = new Thread(new Consumer(st, 10, 1000));
		custom1.setName("custom1");
		Thread custom2 = new Thread(new Consumer(st, 10, 2000));
		custom2.setName("custom2");
		Thread custom3 = new Thread(new Consumer(st, 10, 2000));
		custom3.setName("custom3");
		custom1.start();
		// custom2.start();
		// custom3.start();
		Thread producer1 = new Producer(st, 10, 3000);
		producer1.setName("producer1");
		Thread producer2 = new Producer(st, 30, 3000);
		producer2.setName("producer2");
		Thread producer3 = new Producer(st, 20, 3000);
		producer3.setName("producer3");
		producer1.start();
		producer2.start();
		producer3.start();

	}

}

class Consumer implements Runnable {
	// 每次消费的产品数量
	private int num;
	// 消费频率
	private int time;
	// 所在放置的仓库
	private Storage storage;

	public Consumer(Storage st, int num, int time) {
		this.storage = st;
		this.num = num;
		this.time = time;
	}

	@Override
	public void run() {
		for (;;) {
			try {
				// 2秒消费一次
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ",想要消费个数："
					+ num);
			// 调用仓库Storage的消费函数
			storage.consume(num, Thread.currentThread().getName());
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

}

class Producer extends Thread {
	// 每次生产的产品数量
	private int num;
	// 生产频率
	private int time;
	// 所在放置的仓库
	private Storage storage;

	public Producer(Storage st, int num, int time) {
		this.setStorage(st);
		this.num = num;
		this.time = time;

	}

	public void run() {
		for (;;) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ",想要生产个数："
					+ num);
			// 调用仓库Storage的生产函数
			storage.produce(num, Thread.currentThread().getName());
		}
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

class Storage {
	// 仓库存储体
	private LinkedList<Object> list = new LinkedList<Object>();
	// 仓库最大存储量
	private final int MAX_SIZE = 200;

	// 使用lock解决同步问题
	private final Lock lock = new ReentrantLock();

	private final Condition full = lock.newCondition();

	private final Condition empty = lock.newCondition();

	public Storage() {
		System.out.println("仓库初始化：容量为" + list.size());
	}

	/*
	 * 生产num个产品
	 */
	public void produce(int num, String threadName) {
		// synchronized(list){
		lock.lock();
		try {
			int n = 0;
			while ((n = list.size()) + num > MAX_SIZE) {
				System.out.println(threadName + "【要生产的产品数量】:" + num
						+ " \t 【库存量】:" + n + " \t 【最大存量】:" + MAX_SIZE
						+ "\t 暂时不能执行生产任务!");
				try {
					// 由于条件不满足，生产阻塞
					// list.wait();
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(threadName + "【正在紧张生产中...】");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 生产条件满足情况下，生产num个产品
			for (int i = 0; i < num; i++) {
				list.add(new Object());
			}
			System.out.println(threadName + "【已经生产产品数】:" + num + "\t 【现仓储量为】:"
					+ list.size());
			// list.notifyAll();
		} finally {
			full.signalAll();
			empty.signalAll();
			lock.unlock();
		}
		// }

	}

	/*
	 * 消费num个产品
	 */
	public void consume(int num, String threadName) {
		// 如果仓库存储量不足
		// synchronized(list){
		lock.lock();
		try {
			while (num > list.size()) {
				System.out.println(threadName + "【要消费的产品数量】:" + num
						+ " \t【库存量不足】:" + list.size() + " \t 暂时不能执行消费任务!");
				try {
					// 由于条件不满足，消费阻塞
					// list.wait();
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(threadName + "【已经开始消费...】");
			// 消费条件满足情况下，消费num个产品
			for (int i = 0; i < num; i++) {
				list.remove();
			}
			System.out.println(threadName + "【已经消费产品数】:" + num + " \t 【现仓储量为】:"
					+ list.size());
			// list.notifyAll();

		} finally {
			full.signalAll();
			empty.signalAll();
			lock.unlock();
		}
		// }
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
}