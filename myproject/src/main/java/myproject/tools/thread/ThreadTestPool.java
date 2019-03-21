package myproject.tools.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * demo 线程pool
 * 
 * @author crh 2019年2月15日
 *
 */
public class ThreadTestPool {
	public static void main(String[] args) {
		/**
		 * 1.根据需要创建新的线程，核心线程为0，空闲时间60m，即线程空闲时间超过60秒时，销毁；
		 */
/* 		ExecutorService threadPool1 = Executors.newCachedThreadPool();
		for (int i = 0; i < 1100; i++) {
			threadPool1.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		threadPool1.shutdown();*/
		/**
		 * 2.创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程；核心线程和最大数一致，不回收线程，注意是队列无限；默认拒绝策略 
		 */
/*		ExecutorService threadPool2 =Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			threadPool2.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
 						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		threadPool2.shutdown();*/
		/**
		 * 3.单线程执行任务，按添加的顺序执行，不限制队列；
		 */
/*		ExecutorService threadPool3 =Executors.newSingleThreadExecutor();
		for(int i=0;i<10;i++){
			threadPool3.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
 						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		threadPool3.shutdown();*/
 		/**4.拒绝策略测试
		 * 	1)AbortPolicy()  拒绝新任务并抛出异常RejectedExecutionException
			2)DiscardPolicy  悄悄的抛弃被拒绝的任务
			3)DiscardOldestPolicy 抛弃最先加入队列的任务，然后在重试execute(被拒绝任务)
			4)CallerRunsPolicy   
			直接在执行线程池execute方法的线程中运行被拒绝的任务，除非执行程序已被关闭，
			否则这个任务被丢弃。（比如在main函数中测试线程池，当前策略下，将由main线程来执行被拒绝的任务）
		 */
/*		ExecutorService threadPool4 = new ThreadPoolExecutor(2, 2, 10,
				TimeUnit.SECONDS, new LinkedBlockingDeque<>(6),
				new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 10; i++) {
			final int  n=i;
			threadPool4.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"::"+n);
				}
			});
		}
		threadPool4.shutdown();*/
 		/**5.线程返回值的demo
		 * 单个任务futuretask，通过组合的方式，封装了callable(call方法)，提供runnable接口的功能(run方法)，适配器模式
		 * thread封装了runnable接口，并增强了run方法，装饰模式
		 */
		System.out.println("f1-demo-开始");
		FutureTask<Integer> f1=new FutureTask<>(new Callable<Integer>() {
 			@Override
			public Integer call() throws Exception {
 				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
 				return RandomUtils.nextInt(0,20);
			}
		});
		Thread t1=new Thread(f1);
		t1.start();
		try {
			System.out.println(f1.get());//等待任务完成
			System.out.println("f1-demo-结束");
		} catch (InterruptedException e) {
 			e.printStackTrace();
		} catch (ExecutionException e) {
 			e.printStackTrace();
		}
		/**
		 * 6.线程池返回值的demo,
		 * 每一个Callable是一个带有返回值的任务，返回的结果保存在future中
		 */
		ExecutorService threadPool5=Executors.newFixedThreadPool(10);
		List<Future<Integer>> list=new ArrayList<Future<Integer>>();
		for(int i=0;i<100;i++){
			//submit提交后，最终还是封装到 new FutureTask<T>(callable);
			//执行FutureTask的run方法，将call的结果保存在result中;通过get方法读取
			Future<Integer> fu=threadPool5.submit(new Callable<Integer>() {
	 			@Override
				public Integer call() throws Exception {
	 				try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
 					return RandomUtils.nextInt(0,100);
				}
			});
			list.add(fu);
		}
  		for(Future<Integer> fu:list){
			try {
				System.out.println(fu.get());
			} catch (InterruptedException e) {
 				e.printStackTrace();
			} catch (ExecutionException e) {
 				e.printStackTrace();
			}
		}
  		threadPool5.shutdown();
   	}
}

class Thread7 implements Callable<Object> {
	@Override
	public Object call() {
		return RandomUtils.nextInt(0, 20);
	}

}
