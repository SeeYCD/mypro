package myproject.tools.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class ThreadTest {
	// @Test
	public void test1() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<Integer> future = threadPool.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		});
		try {
			Thread.sleep(2000);
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void test2() {
		@SuppressWarnings("unchecked")
		FutureTask<Integer> future = new FutureTask<Integer>(new testc());
		new Thread(future).start();
		try {
			Thread.sleep(2000);// 可能做一些事
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		@SuppressWarnings("unchecked")
		Future<Integer>[] futures = new Future[5];
		for (int i = 0; i < 5; i++) {
			futures[i] = threadPool.submit(new Callable<Integer>() {
				public Integer call() throws Exception {
					System.out.println("get-");
					return new Random().nextInt(100);
				}
			});
		}
		try {
			Thread.sleep(2000);
			for (int i = 0; i < 5; i++) {
				System.out.println(futures[i].get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("hiding")
	class testc<Integer> implements Callable<Object> {
		public testc() {
		}

		@Override
		public Object call() throws Exception {
			return new Random().nextInt(100);
		}

	}
}
