package myproject.tools.bank;

public class Bank {
    public Bank(int  mone,String cardN) {
    	this.setMoney(mone);
    	this.cardNo=cardN;
 	}
 	// åè®¾ä¸?ä¸ªè´¦æ·æ1000åé±
    private int money ;
    private String cardNo;
    // æå°åé±çæ¹æ³?
    public synchronized void getCounter(int money1) {
         this.setMoney(this.getMoney() - money1);// åé±åæ?»æ°åå°
     }

    // ATMåé±çæ¹æ³?
    public synchronized void getATM(int money2) {// åæ°æ¯æ¯æ¬¡åèµ°çé?
     	this.setMoney(this.getMoney() - money2);// åé±åæ?»æ°åå°
    }

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		return true;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
