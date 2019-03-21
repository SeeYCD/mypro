package myproject.tools.optimisticlock;

import org.junit.Test;

/**
 * 测试volatile关键字，不是线安全的
 * @author user 
 *
 */ 
public class VolatileTest {
	private volatile int i=0;
	public void incresea(){
		i++;//i虽然是volatile修饰的，但是i++得操作不是原子性的；中间执行时可能被其他的线程修改值；
	}
	@Test
	public void test1(){
		 final VolatileTest vl=new VolatileTest();
		for (int i=0;i<10;i++){
			new Thread(){
				public void run() {
 					for(int j=0;j<10000;j++){
 	 					vl.incresea();
  					}
				}
		    }.start();
		}
		while(Thread.activeCount()>2){
			System.out.println(Thread.activeCount());
			Thread.yield();
		}
		System.out.println(vl.getI());
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
}
