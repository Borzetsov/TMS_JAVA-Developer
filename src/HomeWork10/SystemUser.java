/**
 * Classname    SystemUser
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class SystemUser {

    private String name;
    private final String login;
    private String password;

    public SystemUser(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Пользователь:\t" + this.name + "\n\r" +
                "Логин:\t\t\t" + this.login + "\n\r" +
                "Пароль:\t\t\t********";
    }

    /**
     * Самодельный equals
     * Сравнение по имени и логину
     * Не использовал возможности IDE
     * @param o     Объект для сравнения
     * @return      Результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        //Проверить ссылки
        if (o == this) return true;
        //Проверить класс
        if (o.getClass() != SystemUser.class) return false;
        //Для проверки полей потребуется преобразовать объект
        SystemUser eqSystemUser = (SystemUser) o;
        //Проверить имя
        if (!eqSystemUser.name.equals(this.name)) return false;
        //Проверить логин
        if (!eqSystemUser.login.equals(this.login)) return false;
        else return true;
    }

    /**
     * Самодельный hashCode
     * LRC16 от имени и логина
     * Не использовал возможности IDE
     * @return  hash-код
     */
    @Override
    public int hashCode() {
        String str = this.name + this.login;
        char[] hashData = str.toCharArray();
        int hashCode = 0;
        for (char currentChar : hashData) {
            hashCode += currentChar;
        }
        hashCode = hashCode & 0xFFFF;
        hashCode = 0xFFFF - hashCode;
        return Math.abs(hashCode);
    }
}
