package myproject.tools.bank;

public class AMT  extends Thread {
 	// 创建银行对象
    Bank bank;
    private String type;
    // 通过构�?�器传入银行对象，确保两个人进入的是�?个银�?
    public AMT(Bank bank,String typ) {
        this.bank = bank;
        this.type=typ;
    }

    // 重写run方法，在里面使用柜台取钱

    @Override
    public void run() {
        while (bank.getMoney() >= 100) {
            bank.getCounter(100);// 每次取走100�?
            System.out.println(type+"取钱:100,余额�?"+bank.getMoney());
             try {
                sleep(100);// 取完休息0.1�?
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
