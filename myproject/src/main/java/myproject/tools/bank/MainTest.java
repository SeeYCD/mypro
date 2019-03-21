package myproject.tools.bank;

public class MainTest {
    public static void main(String[] args) {
        Bank bank = new Bank(300,"6222202");
        Bank bank2 = new Bank(300,"6222202");
        AMT pA = new AMT(bank,"ATM");
        InterATM pB = new InterATM(bank2,"interATM");
        // 两个人开始取�?
        pA.start();
        pB.start();
    }
}
