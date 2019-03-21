package myproject.tools.lock;

import org.junit.Test;

public class TestLock {
	/**
	 * 测试 synchronized 
	 */
	@Test
	public void test1(){
		Accont ac=new Accont();
		ac.setBalance(1000);
		Bank bank=new Bank(ac);
		Company com=new Company(ac);
		Thread tbank=new Thread(bank);
		Thread tcom=new Thread(com);
		tbank.setPriority(5);
 		tbank.start();
		tcom.start();
 		try{
 			tbank.join();
 			tcom.join();
 			System.out.printf("tbank执行结束 Balance: %f\n",ac.getBalance());
			System.out.printf("Account : Final Balance: %f\n",ac.getBalance());
 		}catch(Exception e){
			
		}

	}
}
