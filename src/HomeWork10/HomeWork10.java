/**
 * Classname    HomeWork10
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class HomeWork10 {

    public void run() throws CloneNotSupportedException {
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

        System.out.println("Задача *");
        System.out.println();

        User user4 = new User(new Person());
        user4.getPerson().setName(new Name("Иван", "Иванович", "Иванов"));
        User user5 = new User(new Person());
        user5.getPerson().setName(new Name("Петр", "Петрович", "Петров"));
        User user6 = new User(new Person());
        user6.getPerson().setName(new Name("Михаил", "Михайлович", "Михайлов"));
        User user7 = new User(new Person());
        user7.getPerson().setName(new Name("Акакий", "Акакиевич", "Акакиев"));

        User[] users = {user4, user5, user6, user7};

        for (User currentUser : users) System.out.println(currentUser);
        System.out.println();

        User cloneUserShallow = users[0].getClone(users, 0, false);
        System.out.println("Клон");
        System.out.println(cloneUserShallow);
        System.out.println();

        System.out.println("Меняем имя пользователя 0");
        user4.getPerson().setName(new Name("Виктор", "Викторович", "Викторов"));
        System.out.println(user4);
        System.out.println("Клон");
        System.out.println(cloneUserShallow);
        System.out.println();

        User cloneUserDeep = users[1].getClone(users, 1, true);
        System.out.println("Клон");
        System.out.println(cloneUserDeep);
        System.out.println();

        System.out.println("Меняем имя пользователя 1");
        user5.getPerson().setName(new Name("Виктор", "Викторович", "Викторов"));
        System.out.println(user5);
        System.out.println("Клон");
        System.out.println(cloneUserDeep);
        System.out.println();
    }
}
