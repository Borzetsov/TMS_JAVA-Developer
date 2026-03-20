package HomeWork6;

public class CreditCard {

    private long accountID;         /*Номер счета*/
    private long currentBalance;    /*Текущий баланс*/

    public void addMoney(long sum){
        this.currentBalance += sum;
    }

    public void getInfo(){
        System.out.println("****************************************");
        System.out.printf("Номер счета:\t%016d\r\n", this.accountID);
        System.out.println("Текущий баланс:\t" + this.currentBalance);
        System.out.println("****************************************");
    }
}
