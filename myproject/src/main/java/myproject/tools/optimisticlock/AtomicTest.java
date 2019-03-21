package myproject.tools.optimisticlock;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicTest {
	public static AtomicInteger atomic=new AtomicInteger(0);
	public static void increase(){
		atomic.getAndIncrement();
	}
	@Test
	public void test1(){
		Thread[] threadS=new Thread[20];
		for(int i=0;i<threadS.length;i++){
			threadS[i]=new Thread(new Runnable() {
 				@Override
				public void run() {
 					for(int i=0;i<10;i++){
 						increase();
 					}
				}
			});
			threadS[i].start();
			try {
				threadS[i].join();
 			} catch (InterruptedException e) {
 				e.printStackTrace();
			}
 		}
//		while(Thread.activeCount()>1)
//		Thread.yield();
		System.out.println(atomic); 
		/*System.out.println(Thread.activeCount());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
		System.out.println(Thread.activeCount());
 		System.out.println(atomic);*/
	}
}
