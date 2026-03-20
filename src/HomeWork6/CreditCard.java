package HomeWork6;

public class CreditCard {

    private final long accountID;   /*Номер счета*/
    private long currentBalance;    /*Текущий баланс*/

    /**
     * Создание счета
     */
    public CreditCard(){
        System.out.println("Создание счета");
        this.accountID = this.generateID();
        System.out.printf("Создан счет\t\t\t%016d", this.accountID);
        System.out.println();
        System.out.println("Текущий баланс:\t\t" + this.currentBalance);
        System.out.println();
    }

    /**
     * Создание счета
     * @param initialBalance    начальный баланс
     */
    public CreditCard(long initialBalance){
        System.out.println("Создание счета");
        this.accountID = this.generateID();
        this.currentBalance = initialBalance;
        System.out.printf("Создан счет\t\t\t%016d", this.accountID);
        System.out.println();
        System.out.println("Текущий баланс:\t\t" + this.currentBalance);
        System.out.println();
    }

    /**
     * Запрос пополнения баланса
     * @param sum   сумма пополнения
     */
    public void addMoney(long sum){
        //Пополнить баланс
        System.out.printf("Пополнение счета\t%016d\r\n", this.accountID);
        System.out.println("на сумму\t\t\t" + sum);
        this.currentBalance += sum;
        System.out.println("Успешно.");
        System.out.println("Текущий баланс:\t\t" + this.currentBalance);
        System.out.println();
    }

    /**
     * Запрос снять сумму
     * @param sum   требуемая сумма
     * @return      сумма, которую удалось снять
     */
    public long takeMoney(long sum){
        //Возможная проверка на снятие.
        //Уменьшить баланс
        System.out.printf("Снятие средств\r\nсо счета\t\t\t%016d\r\n", this.accountID);
        System.out.println("в размере\t\t\t" + sum);
        this.currentBalance -= sum;
        //Вернуть сумму, которую удалось снять со счета
        System.out.println("Успешно.");
        System.out.println("Текущий баланс:\t\t" + this.currentBalance);
        System.out.println();
        return sum;
    }

    /**
     * Запрос информации о счете
     */
    public void getInfo(){
        System.out.println("****************************************");
        System.out.printf("Номер счета:\t\t%016d\r\n", this.accountID);
        System.out.println("Текущий баланс:\t\t" + this.currentBalance);
        System.out.println();
    }

    /**
     * Создание номера счета
     * @return      номер счета
     */
    private long generateID(){
        long newID = 0;
        for (int i = 0; i < 5; i++){
            newID += (int)(Math.random() * 255);
            newID = newID << 8;
        }
        return newID;
    }
}
