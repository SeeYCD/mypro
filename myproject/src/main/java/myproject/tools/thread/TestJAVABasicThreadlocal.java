package myproject.tools.thread;
/**
 * 基础测试threadlocal 线程本地变量
 * 
 * @author user
 */
public class TestJAVABasicThreadlocal {
	public static void main(String[] args) throws InterruptedException {
		ThreadDemo td=new ThreadDemo(10);
		ThreadDemoDo tdd=new ThreadDemoDo(td);

		Thread t1=new Thread(tdd);
		t1.start();
		Thread t2=new Thread(tdd);
		t2.start();
		t1.join();
		t2.join();
		System.out.println(td.getDd());
		System.out.println(td.getTd());
 	}
	
}
class ThreadDemoEntity{
	private String name;
	public ThreadDemoEntity(String n){
		this.name=n;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 	
}
class ThreadDemo{
	ThreadLocal<Integer> dd=new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
	        return 0;
	    }
	};
	private ThreadLocal<ThreadDemoEntity> td=new ThreadLocal<ThreadDemoEntity>(){
		@Override
		protected ThreadDemoEntity initialValue() {
	        return new ThreadDemoEntity("demo");
	    }
	};
	public ThreadDemo(int n){
 		dd.set(n);
	}
	public Integer getDd() {
		return dd.get();
	}
 	public void setDd() {
 		dd.set(dd.get()+10); 
	}
	public String getTd() {
		ThreadDemoEntity t=this.td.get();
 		return t.getName();
	}
	public void setTd(String n) {
		ThreadDemoEntity t=this.td.get();
		t.setName(n);
	}
 }
class ThreadDemoDo implements Runnable{
	private ThreadDemo bk;
	public ThreadDemoDo(ThreadDemo bk){
		this.bk=bk;
	}
 	@Override
	public void run() {
   		for(int i=0;i<10;i++){
 			bk.setDd();
 			System.out.println(Thread.currentThread().getName()
 					+":"+bk.getDd());
 			bk.setTd(Thread.currentThread().getName());
 			System.out.println(Thread.currentThread().getName()
 					+":"+bk.getTd());
 		}
	}

}

