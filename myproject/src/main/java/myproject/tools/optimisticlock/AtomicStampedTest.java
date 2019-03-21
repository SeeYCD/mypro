package myproject.tools.optimisticlock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
/**
 * 测试解决ABA问题
 * @author crh 2019年3月19日
 *
 */
public class AtomicStampedTest {
	public static AtomicInteger atomic = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(
				0, 0);
		Integer reference = atomicStampedReference.getReference();
		int stamp = atomicStampedReference.getStamp();
		Thread th1 = new Thread(() -> {
			int stampT = stamp;
			int newStamp = stamp + 1;
			boolean b1 = atomicStampedReference.compareAndSet(reference,
					reference + 1, stampT, newStamp);
			System.out.println("th1 result b1 reference" + reference);
			System.out.println("th1 result b1" + b1);
			Integer reference2 = atomicStampedReference.getReference();
			stampT=newStamp;
			newStamp = stampT + 1;
			boolean b2 = atomicStampedReference.compareAndSet(reference2,
					reference2 - 1, stampT, newStamp);
			System.out.println("th1 result b2" + b2);
			System.out.println("th1 result b2 reference" + atomicStampedReference.getReference());


		});
		Thread th2 = new Thread(() -> {
			int stampT = stamp;
			int newStamp = stampT + 1;
			System.out.println("th2 result reference" + reference);
 			boolean b = atomicStampedReference.compareAndSet(reference,
					reference + 1, stampT, newStamp);
			System.out.println("th2 result " + b);
		});
		th1.start();
		th1.join();
		th2.start();
		th2.join();
	}
}
