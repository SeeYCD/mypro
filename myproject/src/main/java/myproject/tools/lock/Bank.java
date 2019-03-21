package myproject.tools.lock;

public class Bank implements Runnable{
	private Accont accont;
	public Bank(Accont ac){
		this.accont=ac;
	}
	@Override
	public void run() {
		synchronized (accont) {
			System.out.println("begin wait() ThreadName="+ Thread.currentThread().getName());
 			try {
				accont.wait(2000);
//				Thread.sleep(2000);
 			} catch (InterruptedException e) {
 				e.printStackTrace();
			}
			System.out.println("end wait() ThreadName=" + Thread.currentThread().getName());
		}
	}
	
}
