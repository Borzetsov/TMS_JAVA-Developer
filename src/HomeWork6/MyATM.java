package HomeWork6;

public class MyATM {

    private int paper20 = 0;        //Количество купюр номиналом 20
    private int paper50 = 0;        //Количество купюр номиналом 50
    private int paper100 = 0;       //Количество купюр номиналом 100

    private long currentBalance = 0L;    //Остаток средств в банкомате

    /**
     * Создание пустого банкомата
     */
    public MyATM(){
        System.out.println("Установка банкомата");
        System.out.println("Успешно");
        System.out.println();
    }

    /**
     * Создание банкомата с купюрами номиналом 20
     * @param add20     количество купюр номиналом 20
     */
    public MyATM(int add20){
        System.out.println("Установка банкомата");
        this.paper20 = add20;
        this.updateBalance();
        System.out.println("Успешно");
        System.out.println();
    }

    /**
     * Создание банкомата с купюрами номиналом 20 и 50
     * @param add20     количество купюр номиналом 20
     * @param add50     количество купюр номиналом 50
     */
    public MyATM(int add20, int add50){
        System.out.println("Установка банкомата");
        this.paper20 = add20;
        this.paper50 = add50;
        this.updateBalance();
        System.out.println("Успешно");
        System.out.println();
    }

    /**
     * Создание банкомата с купюрами номиналом 20, 50 и 100
     * @param add20     количество купюр номиналом 20
     * @param add50     количество купюр номиналом 50
     * @param add100    количество купюр номиналом 100
     */
    public MyATM(int add20, int add50, int add100){
        System.out.println("Установка банкомата");
        this.paper20 = add20;
        this.paper50 = add50;
        this.paper100 = add100;
        this.updateBalance();
        System.out.println("Успешно");
        System.out.println();
    }

    /**
     * Загружает купюры в банкомат
     * @param add20     количество купюр номиналом 20
     * @param add50     количество купюр номиналом 50
     * @param add100    количество купюр номиналом 100
     */
    public void addPaper(int add20, int add50, int add100){
        System.out.println("Загрузка купюр");
        this.paper20 += add20;
        this.paper50 += add50;
        this.paper100 += add100;
        this.updateBalance();
        System.out.println("Успешно");
        System.out.println();
    }

    /**
     * Выводит остаток и количество купюр в банкомате
     */
    public void getInfo(){
        System.out.println("Остаток средств в банкомате:\t" + this.currentBalance);
        System.out.println("Купюры в наличии:");
        System.out.println("Номинал\t\t20\t50\t100");
        System.out.println("Количество\t" + this.paper20 + "\t" + this.paper50 + "\t" + this.paper100);
        System.out.println();
    }

    /**
     * Есть ли купюры
     * @return      статус
     */
    public boolean havePaper(){
        if (this.paper20 + this.paper50 + this.paper100 == 0) return false;
        else return true;
    }

    /**
     * Запрос на снятие средств
     * @param sum   требуемая сумма
     * @return      статус
     */
    public boolean takeMoney(long sum){
        System.out.println("Запрос на снятие средств");
        System.out.println("в размере\t" + sum);
        //Предварительная проверка
        if (!this.checkSum(sum)) return false;
        //Сколько купюр потребуется?
        long restSum = sum;
        int usePaper20 = 0;
        int usePaper50 = 0;
        int usePaper100 = 0;
        //Можно ли сдать только купюрами по 100?
        if (sum % 100 == 0){
            usePaper100 = (int)(sum / 100);
            if (usePaper100 <= this.paper100){
                this.paper100 -= usePaper100;   //Выдать купюры
                this.updateBalance();           //Обновить баланс
                System.out.println("Успешно");
                System.out.println("Использовано купюр:");
                System.out.println("Номинал\t\t20\t50\t100");
                System.out.println("Количество\t" + usePaper20 + "\t" + usePaper50 + "\t" + usePaper100);
                System.out.println();
                return true;
            }
        }
        //Можно ли сдать только купюрами по 50?
        if (sum % 50 == 0){
            usePaper50 = (int)(sum / 50);
            if ((usePaper50 <= this.paper50) && (usePaper50 < 2)){
                this.paper50 -= usePaper50;     //Выдать купюры
                this.updateBalance();           //Обновить баланс
                System.out.println("Успешно");
                System.out.println("Использовано купюр:");
                System.out.println("Номинал\t\t20\t50\t100");
                System.out.println("Количество\t" + usePaper20 + "\t" + usePaper50 + "\t" + usePaper100);
                System.out.println();
                return true;
            }
        }
        //Можно ли сдать только купюрами по 20?
        if (sum % 20 == 0){
            usePaper20 = (int)(sum / 20);
            if ((usePaper20 <= this.paper20) && (usePaper20 < 5)){
                this.paper20 -= usePaper20;     //Выдать купюры
                this.updateBalance();           //Обновить баланс
                System.out.println("Успешно");
                System.out.println("Использовано купюр:");
                System.out.println("Номинал\t\t20\t50\t100");
                System.out.println("Количество\t" + usePaper20 + "\t" + usePaper50 + "\t" + usePaper100);
                System.out.println();
                return true;
            }
        }
        //Выдача купюрами с разным номиналом
        usePaper20 = 0;
        usePaper50 = 0;
        usePaper100 = 0;

        //Сколько потребуется купюр номиналом 100
        usePaper100 += (int)(restSum / 100);
        if (usePaper100 > this.paper100) usePaper100 = this.paper100;   //Взять все купюры номиналом 100
        //Уточнить остаток суммы
        restSum -= (long)usePaper100 * 100;

        //Если использовали купюры номиналом 100 и остаток равен 10
        if ((usePaper100 > 0) && (restSum == 10)){
            usePaper100--;          //-100
            usePaper20 += 3;        //+60
            usePaper50++;           //+50
            restSum -= 10;
        }

        //Если использовали купюры номиналом 100 и остаток равен 30
        if ((usePaper100 > 0) && (restSum == 30)){
            usePaper100--;          //-100
            usePaper20 += 4;        //+80
            usePaper50++;           //+50
            restSum -= 30;
        }

        if (restSum != 0){
            //Сколько потребуется купюр номиналом 50
            usePaper50 += (int) (restSum / 50);
            if (usePaper50 > this.paper50) usePaper50 = this.paper50;   //Взять все купюры номиналом 50
            //Уточнить остаток суммы
            restSum -= (long) usePaper50 * 50;

            //Если использовали купюры номиналом 50 остаток равен 10
            if ((usePaper50 > 0) && (restSum == 10)){
                usePaper50--;           //-50
                usePaper20 += 3;        //+60
                restSum -= 10;
            }

            //Если использовали купюры номиналом 50 остаток равен 30
            if ((usePaper50 > 0) && (restSum == 30)){
                usePaper50--;           //-50
                usePaper20 += 4;        //+80
                restSum -= 30;
            }
        }

        if (restSum != 0) {
            //Сколько потребуется купюр номиналом 20
            usePaper20 += (int) (restSum / 20);
            if (usePaper20 > this.paper20) usePaper20 = this.paper20;   //Взять все купюры номиналом 20
            //Уточнить остаток суммы
            restSum -= (long) usePaper20 * 20;

            //Если использовали купюры номиналом 20 остаток равен 10
            if ((usePaper20 > 0) && (restSum == 10)){
                usePaper50++;           //+50
                usePaper20 -= 2;        //-40
                restSum -= 10;
            }
        }

        if (restSum != 0) {
            if ((usePaper20 > this.paper20) || (usePaper50 > this.paper50) || (usePaper100 > this.paper100))
                System.out.println("Не удалось определить состав выдачи");
            return false;
        }

        if ((usePaper20 < 0) || (usePaper50 < 0) || (usePaper100 < 0)){
            System.out.println("Не удалось определить состав выдачи");
            return false;
        }

        System.out.println("Успешно");
        //Выдать купюры
        this.paper20 -= usePaper20;
        this.paper50 -= usePaper50;
        this.paper100 -= usePaper100;
        this.updateBalance();
        System.out.println("Использовано купюр:");
        System.out.println("Номинал\t\t20\t50\t100");
        System.out.println("Количество\t" + usePaper20 + "\t" + usePaper50 + "\t" + usePaper100);
        System.out.println();
        return  true;
    }

    /**
     * Проверяет возможность выдать деньги
     * @param sum   требуемая сумма
     * @return      статус
     */
    private boolean checkSum(long sum){
        //Проверка суммы (меньше 20)
        if (sum < 20){
            System.out.println("Недопустимая сумма");
            return false;
        }
        //Проверка суммы (не кратна 10)
        if  (sum % 10 != 0){
            System.out.println("Недопустимая сумма");
            return false;
        }
        //Хватит ли денег?
        if (sum > this.currentBalance){
            System.out.println("В банкомате недостаточно средств");
            return false;
        }
        return  true;
    }

    /**
     * Обновляет текущий баланс банкомата
     */
    private void updateBalance(){
        this.currentBalance = (long)this.paper20 * 20 + (long)this.paper50 * 50 + (long)this.paper100 * 100;
    }
}
