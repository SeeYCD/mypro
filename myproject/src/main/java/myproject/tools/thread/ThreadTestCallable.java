package myproject.tools.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * demo 线程获取返回值
 * 
 * @author crh 2019年2月15日
 *
 */
public class ThreadTestCallable {
	public static void main(String[] args) {
		FutureTask<Object> fu = new FutureTask<Object>(new Thread6());
		//内部类方式
		FutureTask<Object> fu2 = new FutureTask<Object>(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return new Random().nextInt(100);
			}
		});
		new Thread(fu2).start();
		new Thread(fu).start();
		try {
			System.out.println("fu:"+fu.get());
			System.out.println("fu:"+fu.get(2,TimeUnit.SECONDS));
			System.out.println("fu2:"+fu2.get());
 		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
 			e.printStackTrace();
		}
	}
}

class Thread6 implements Callable<Object> {
	@Override
	public Object call() {
		return RandomUtils.nextInt(0, 20);
	}

}

