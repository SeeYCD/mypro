package myproject.tools.lock;

public class Accont {
	private  double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public  synchronized void addAmount(double amount) {
		         double tmp=balance;
		         try {
		             Thread.sleep(100);
		         } catch (InterruptedException e) {
		            e.printStackTrace();
		         }
		         tmp+=amount;
		         balance=tmp;
		         System.out.println("+:"+amount);
	 }
     public synchronized void subtractAmount(double amount) {
         double tmp=balance;
         try {
             Thread.sleep(100);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         tmp-=amount;
         balance=tmp;
         System.out.println("-:"+amount);
      }

}
