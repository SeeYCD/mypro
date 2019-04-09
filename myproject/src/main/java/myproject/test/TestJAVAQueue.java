package myproject.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础测试
 * 
 * @author user
 *
 */
public class TestJAVAQueue {
	/**
	 * queue,队列的超类拥有六个方法，单端队列 
	 * add:底层调用offer，当queue-full时，抛出异常；IllegalStateException 
	 * offer:添加到队列尾部，当element为null时报错空指针,当元素无法添加时，返回false
	 * 
	 * remove:获取并且移除头部元素，NoSuchElementException if this queue is empty
	 * poll:获取并且移除头部元素,the head of this queue, or null if this queue is empty
	 * 
	 * element:获取头部元素，不移除，NoSuchElementException if this queue is empty
	 * peek:获取头部元素，不移除,null when queue is empty
	 * 
	 * 1.1数组同步队列，ArrayBlockingQueue底层使用数组存储数据；使用ReentrantLock达到同步；读、取使用同一把锁影响效率；
	  *  同一个lock，notFull、notEmpty俩个condition，生产者消费者模型
	 * put方法：添加元素时，队列已满则等待notFull.wait()，否则notEmpty.signal()；
	 * take方法：获取元素时，队列如果为空等待notEmpty.wait()，否则notFull.signal()；
	 * 
	 * 1.2单端链表同步队列 ,LinkedBlockingDeque；使用ReentrantLock达到同步；
	  * 读、取都设置了一把锁，通过原子类来存储数值，操作都是原子性现成安全的；相对于ArrayBlockingQueue，在没有达到等待的情况，，读取互不影响效率有所提升；
	  * 读锁takeLock，读锁的条件notEmpty 
	  * 插入锁putLock，插入锁的notFull
	 */
	private static Logger log = LoggerFactory.getLogger(TestJAVABasic.class);

//	@Test
	public void testArrayBlockingQueue() {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(5);
		// 1.添加元素
		for (int i = 0; i < 10; i++) {
//			log.info(i + "");
// 			bq.add(i);
// 			bq.offer(null);
			System.out.println(bq.offer(i));
		}
		// 2.获取元素，头部开始
		Integer i = 0;
//		while ((i = bq.poll()) != null) {
// 	 		bq.remove();//队列空时，跑出异常
//			System.out.println(i);
//		}
		System.out.println("peek:" + bq.peek());
//		System.out.println("element:" + bq.element());

		// 3.迭代
		Iterator<Integer> it = bq.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	@Test
	public void testLinkedBlockingQueue() {
		BlockingQueue<Integer> bq = new LinkedBlockingDeque<Integer>(11);
		// 1.添加元素
		for (int i = 0; i < 10; i++) {
			log.info(i + "");
 			bq.add(i);
// 			bq.offer(null);
			System.out.println(bq.offer(i));
		}
		// 2.获取元素，头部开始
		Integer i = 0;
//		while ((i = bq.poll()) != null) {
// 	 		bq.remove();//队列空时，跑出异常
//			System.out.println(i);
//		}
		System.out.println("peek:" + bq.peek());
//		System.out.println("element:" + bq.element());
		
		//2.迭代
		Iterator<Integer> it=bq.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}


