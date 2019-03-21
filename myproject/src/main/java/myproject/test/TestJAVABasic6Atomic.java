package myproject.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础测试异常体系
 * 
 * @author user
 *
 */
public class TestJAVABasic6Atomic {
	//原子性操作类，通过unsafe类的原子操作，保证加的动作线程安全
	private AtomicInteger at = new AtomicInteger(0);
	public void incrementOne() {
		at.getAndIncrement();
//		at.incrementAndGet();
	}
	public static void main(String[] args) {
		final TestJAVABasic6Atomic tja = new TestJAVABasic6Atomic();
		System.out.println("初始值："+tja.at.intValue());
 		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 1; i++) {
						tja.incrementOne();
					}
				}
			}).start();
		}
		//让所有线程跑完，因为自旋等待消耗时间，所以比较慢
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("实际结果："+tja.at);
	}
}
