package myproject.tools.lock;


public class Company implements Runnable{
	 private Accont accont;
	 public Company(Accont ac){
			this.accont=ac;
		}
	@Override
	public void run() {
		/*for(int i=0;i<20;i++){
			accont.addAmount(1000);
 		} */
		try {
			synchronized (accont) {
				System.out.println("begin notify() ThreadName="+ Thread.currentThread().getName());
//				Thread.sleep(5000);
				accont.wait(2000);
				Thread.yield();
//				accont.notify();
 				System.out.println("end notify() ThreadName=" + Thread.currentThread().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
