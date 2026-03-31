/**
 * Classname    HomeWork10
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class HomeWork10 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №10 | Выдано 30.03.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        SystemUser user1 = new SystemUser("Алексей", "alexRandom", "123");
        SystemUser user2 = new SystemUser("Олег", "allEgg", "!15asd@");
        SystemUser user3 = new SystemUser("Алексей", "alexRandom", "321");
        System.out.println(user1);
        System.out.println(user1.hashCode());
        System.out.println();
        System.out.println(user2);
        System.out.println();
        System.out.println(user3);
        System.out.println();

        System.out.println("Сравнение user1 и user1");
        System.out.println(user1.equals(user1));
        System.out.println("Сравнение HASH user1 и user1");
        System.out.println("HASH user1:\t" + user1.hashCode());
        System.out.println("HASH user1:\t" + user1.hashCode());
        System.out.println();

        System.out.println("Сравнение user1 и user2");
        System.out.println(user1.equals(user2));
        System.out.println("Сравнение HASH user1 и user2");
        System.out.println("HASH user1:\t" + user1.hashCode());
        System.out.println("HASH user2:\t" + user2.hashCode());
        System.out.println();

        System.out.println("Сравнение user2 и user3");
        System.out.println(user2.equals(user3));
        System.out.println("Сравнение HASH user2 и user3");
        System.out.println("HASH user2:\t" + user2.hashCode());
        System.out.println("HASH user3:\t" + user3.hashCode());
        System.out.println();

        System.out.println("Сравнение user1 и user3");
        System.out.println(user1.equals(user3));
        System.out.println("Сравнение HASH user1 и user3");
        System.out.println("HASH user1:\t" + user1.hashCode());
        System.out.println("HASH user3:\t" + user3.hashCode());
        System.out.println();
    }
}
